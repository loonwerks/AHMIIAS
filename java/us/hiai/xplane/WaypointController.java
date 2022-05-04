package us.hiai.xplane;

import java.awt.geom.Point2D;

public class WaypointController {
    public Waypoint waypoints[];
    public int currentWaypoint = 0;

    private double EARTH_RADIUS = 6371.0;
    private double DISTANCE_TO_WAYPOINT_THRESHOLD = 0.5; // When the plane is within this distance of the target waypoint, it will switch to the next waypoint

    public WaypointController(){
        //36.117820739746094,-115.17686462402344
        double startLat= 36.117820739746094, startLon=-115.17686462402344;

        waypoints = new Waypoint[7];
        waypoints[0] = new Waypoint(36.30,-115.17, true);
        waypoints[1] = new Waypoint(36.40,-115.17, true);
        waypoints[2] = new Waypoint(37.2,-115.17);
        waypoints[3] = new Waypoint(38.1,-115.17,true); // Default Landing zone

        waypoints[4] = new Waypoint(36.22688293457031,-115.04660034179688, true); // option 1 Landing
        waypoints[5] = new Waypoint(36.21802520751953,-115.13361358642578, true); // option 2 Landing
        waypoints[6] = new Waypoint(36.117820739746094,-115.17686462402344, true); // option 3 Landing
    }

    public void updateWaypoints(double lat, double lon){
        // If plane is within threshold of waypoint, then update waypoint to next point
        if (getDistanceToWaypoint(lat,lon) <= waypoints[currentWaypoint].threshold) {
            currentWaypoint = (currentWaypoint + 1) % waypoints.length;
        }
    }
    public double calculateHeading(double lat, double lon){
        double lat2 = waypoints[currentWaypoint].getLat(),lon2 = waypoints[currentWaypoint].getLon();
        lat = Math.toRadians(lat);
        lat2 = Math.toRadians(lat2);
        lon = Math.toRadians(lon);
        lon2 = Math.toRadians(lon2);

        double Y = Math.cos(lat2) * Math.sin(lon2-lon);
        double X = (Math.cos(lat) * Math.sin(lat2)) - (Math.sin(lat) * Math.cos(lat2) *Math.cos(lon2-lon));
        double heading_angle = Math.atan2(Y,X);
        heading_angle = Math.toDegrees(heading_angle);

        return heading_angle;
    }

    public double getDistanceToWaypoint(double lat, double lon){

        double lat2 = waypoints[currentWaypoint].getLat(),lon2 = waypoints[currentWaypoint].getLon();
        lat = Math.toRadians(lat);
        lat2 = Math.toRadians(lat2);
        lon = Math.toRadians(lon);
        lon2 = Math.toRadians(lon2);

        /* Haversine formula to calculate great-circle distance*/
        double distance = 0;
        distance = Math.pow(Math.sin((lon-lon2)/2),2);
        distance *= Math.cos(lat)*Math.cos(lat2);
        distance += Math.pow(Math.sin((lat-lat2)/2),2);
        distance = Math.sqrt(distance);
        distance = Math.asin(distance);
        distance *= 2 * EARTH_RADIUS;

        return distance;
    }
    // Calculates distance between two GPS coordinates using the Haversine formula
    public double getDistanceBetweenTwoPoints (double lat, double lon, double lat2, double lon2){

        lat = Math.toRadians(lat);
        lat2 = Math.toRadians(lat2);
        lon = Math.toRadians(lon);
        lon2 = Math.toRadians(lon2);

        /* Haversine formula to calculate great-circle distance*/
        double distance = 0;
        distance = Math.pow(Math.sin((lon-lon2)/2),2);
        distance *= Math.cos(lat)*Math.cos(lat2);
        distance += Math.pow(Math.sin((lat-lat2)/2),2);
        distance = Math.sqrt(distance);
        distance = Math.asin(distance);
        distance *= 2 * EARTH_RADIUS;

        return distance;
    }

    public class Waypoint{
        private double lat, lon;
        public boolean isLandingPoint = false;
        public double threshold = DISTANCE_TO_WAYPOINT_THRESHOLD;
        public Waypoint(double lat, double lon){
            this.lat = lat;
            this.lon = lon;
        }
        public Waypoint(double lat, double lon, boolean isLandingPoint){
            this.lat = lat;
            this.lon = lon;
            this.isLandingPoint = isLandingPoint;
            this.threshold = -1;
        }
        public double getLat(){
            return lat;
        }
        public double getLon(){
            return lon;
        }



    }

}
