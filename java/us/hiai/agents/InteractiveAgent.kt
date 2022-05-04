package us.hiai.agents

import us.hiai.xplane.XPlaneConnector

/**
 * Created by mstafford on 7/6/17.
 */
class InteractiveAgent : XPlaneAgent()
{
    val xpc = XPlaneConnector()

    override fun name(): String = "InteractiveAgent"

    override fun runOnStartup(): Boolean = false

    override fun start()
    {
        var running = true

        while (running)
        {
            print("> ")
            val line = readLine()

            if (line.equals("exit"))
            {
                running = false
            }
            else
            {
                val first = line!!.split(" ")[0]
                val second = line.split(" ")[1]

                when (first)
                {
                    "throttle" -> xpc.setThrottle(second.toFloat())
                    "pitch" -> xpc.setPitch(second.toFloat())
                    "yaw" -> xpc.setYaw(second.toFloat())
                    "roll" -> xpc.setRoll(second.toFloat())
                    "brake" -> xpc.setWheelBrake(second.toBoolean())
                    "gear" -> xpc.setGear(second.toBoolean())
                    "exit" -> running = false
                    "show" -> printValue(second)
                    "watch" -> printUntilInput(second)
                    else -> println("Not Recognized.")
                }
            }
        }
    }

    private fun printUntilInput(metric: String)
    {
        val printThread = Thread {

            try
            {
                while (!Thread.interrupted())
                {
                    printValue(metric)
                    Thread.sleep(200)
                }
            }
            catch (ignored: InterruptedException) {}
        }
        printThread.start()
        readLine()
        printThread.interrupt()
    }

    private fun printValue(metric: String)
    {
        println(
                when (metric)
                {
                    "pitch" -> xpc.currentPitch()
                    "altitude" -> xpc.currentAltitude()
                    "heading" -> xpc.currentHeading()
                    "roll" -> xpc.currentRollAngle()
                    else -> "Not Recognized."
                })
    }
}
