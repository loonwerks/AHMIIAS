package us.hiai.data;

import org.jsoar.util.events.SoarEvent;

/**
 * Created by icislab on 10/13/2016.
 */
public class FlightData implements SoarEvent
{
    public int airspeed = 0;
    public int altitude = 0;
    public double lat = 0;
    public double lon = 0;
    public double throttle = 0;
    public boolean allEningesOK = true;
    public boolean wheelBrakesON;
    public boolean airBrakesON;
    public boolean reversersON;

    public FlightData(int airspeed, int altitude, double lat, double lon, double throttle, boolean allEnginesOK, boolean wBrakes, boolean aBrakes, boolean reversers)
    {
        this.airspeed = airspeed;
        this.altitude = altitude;
        this.lat = lat;
        this.lon = lon;
        this.throttle = throttle;
        this.allEningesOK = allEnginesOK;
        this.wheelBrakesON = wBrakes;
        this.airBrakesON = aBrakes;
        this.reversersON = reversers;
    }

}
