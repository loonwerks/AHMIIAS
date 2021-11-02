package us.hiai.agents

import us.hiai.xplane.XPlaneConnector
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

/**
 * Created by mstafford on 7/6/17.
 */
class AutoTakeoff : XPlaneAgent()
{
    override fun name(): String = "AutoTakeOffAgent"

    override fun runOnStartup(): Boolean = false

    override fun start()
    {
        println("Starting AutoTakeOffAgent.")
        val xpc = XPlaneConnector()

        val heading = Math.round(xpc.currentHeading()).toFloat()
//        val lat = xpc.currentLatitude()
//        val long = xpc.currentLongitude()

        val steerNoseWheel = holdPosition(
                actuator = xpc::setYaw,
                sensor = xpc::currentHeading,
                sensorGoal = heading,
                window = 0.8f,
                actuatorDefault = 0f,
                actuatorChange = 0.3f)

        steerNoseWheel.start()

        xpc.setThrottle(1f)
        xpc.setWheelBrake(false)

        waitWhile { xpc.currentAirspeed() < 130 }

        steerNoseWheel.interrupt()
        xpc.setGear(false)

        val keepAttitude = holdPosition(xpc::setPitch, xpc::currentPitch, 25f, 10f, 0.5f, 0.2f)
        keepAttitude.start()

        val keepRoll = holdPosition(xpc::setRoll, xpc::currentRollAngle, 0f, 10f, 0f, 0.25f)
        keepRoll.start()

        waitWhile {xpc.currentAltitude() < 1000}

        keepAttitude.interrupt()
        keepRoll.interrupt()
    }

    private fun waitWhile(predicate: () -> Boolean)
    {
        while (predicate.invoke())
        {
            waitSeconds(0.1)
        }
    }

    private fun holdPosition(actuator: KFunction1<Float, Unit>, sensor: KFunction0<Float>, sensorGoal: Float,
                             window: Float, actuatorDefault: Float, actuatorChange: Float): Thread
    {
        return Thread {

            val halfWindow = window / 2

            try
            {
                while (!Thread.interrupted())
                {
                    val sensorValue = sensor.invoke()

                    when
                    {
                        sensorValue < sensorGoal - halfWindow -> actuator.invoke(actuatorDefault + actuatorChange)
                        sensorValue > sensorGoal + halfWindow -> actuator.invoke(actuatorDefault - actuatorChange)
                        else -> actuator.invoke(actuatorDefault)
                    }
                    waitSeconds(0.1)
                }
            }
            catch (ignored: InterruptedException) {}
            actuator.invoke(0f)
        }
    }

    private fun waitSeconds(i: Double) = Thread.sleep((i*1000).toLong())
}
