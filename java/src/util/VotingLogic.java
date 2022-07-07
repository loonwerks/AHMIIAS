package util;

public class VotingLogic {
    double TAKEOFF_LANDING_MIN_AGL   = 	0.0;   //Takeoff/Landing AGL Range
    double TAKEOFF_LANDING_MAX_AGL   = 	50.0;  //Takeoff/Landing AGL Range
    double A_D_LOW_ALTITUDE_MAX_AGL  = 	250.0; //Approach/Departure Low Altitude AGL Range
    double A_D_HIGH_ALTITUDE_MAX_AGL = 	1000.0;//Approach/Departure High Altitude AGL Range
    double EN_ROUTE_MAX_AGL          = 	3000.0;//En route AGL Range

    double TAKEOFF_LANDING_MIN_HAL   = 	3.0;   //Takeoff/Landing HAL Range
    double TAKEOFF_LANDING_MAX_HAL   = 	5.0;   //Takeoff/Landing HAL Range
    double A_D_LOW_ALTITUDE_MAX_HAL  = 	40.0;  //Approach/Departure Low Altitude HAL Range
    double A_D_HIGH_ALTITUDE_MAX_HAL =  80.0;  //Approach/Departure High Altitude HAL Range
    double EN_ROUTE_MAX_HAL          = 	150.0; //En route HAL Range

    private final double EARTH_RADIUS = 6371.0;

    public double[] checkForReliability(float[][] sensorPositions, int altitude){
        double horizontalThreshold = calculateHorizontalThreshold(altitude)/1000.0;//in Km

        double GPSvsLIDARdistance = getDistanceBetweenTwoPoints(sensorPositions[0][0],sensorPositions[0][1],sensorPositions[1][0],sensorPositions[1][1]);
        double GPSvsIMUdistance = getDistanceBetweenTwoPoints(sensorPositions[0][0],sensorPositions[0][1],sensorPositions[2][0],sensorPositions[2][1]);
        double LIDARvsIMUdistance = getDistanceBetweenTwoPoints(sensorPositions[1][0],sensorPositions[1][1],sensorPositions[2][0],sensorPositions[2][1]);


        return new double[]{truncate(GPSvsLIDARdistance),truncate(GPSvsIMUdistance),truncate(LIDARvsIMUdistance)};
    }
    private double truncate(double a){
        String num = ""+a+"   ";
        num = num.substring(0,num.indexOf(".")+2);
        return Double.parseDouble(num);


    }

    private double calculateHorizontalThreshold(double altitude /*meters above ground*/){
        // Formula taken from GIT : https://github.com/loonwerks/AHMIIAS/blob/master/architecture/AADL_projects/unreliable-sensor/library/Unreliable_Sensor_Lib.aadl


        double threshold = 0.0;

        if(altitude < TAKEOFF_LANDING_MAX_AGL ) {
            threshold = (TAKEOFF_LANDING_MAX_HAL - TAKEOFF_LANDING_MIN_HAL) / (TAKEOFF_LANDING_MAX_AGL - TAKEOFF_LANDING_MIN_AGL) * altitude + TAKEOFF_LANDING_MIN_HAL;
        }else if (altitude < A_D_LOW_ALTITUDE_MAX_AGL) {
            threshold = (A_D_LOW_ALTITUDE_MAX_HAL - TAKEOFF_LANDING_MAX_HAL) / (A_D_LOW_ALTITUDE_MAX_AGL - TAKEOFF_LANDING_MAX_AGL) * altitude + TAKEOFF_LANDING_MAX_HAL;
        }else if (altitude < A_D_HIGH_ALTITUDE_MAX_AGL) {
            threshold = (A_D_HIGH_ALTITUDE_MAX_HAL - A_D_LOW_ALTITUDE_MAX_HAL) / (A_D_HIGH_ALTITUDE_MAX_AGL - A_D_LOW_ALTITUDE_MAX_AGL) * altitude + A_D_LOW_ALTITUDE_MAX_HAL;
        }else if (altitude < EN_ROUTE_MAX_AGL) {
            threshold = (EN_ROUTE_MAX_HAL - A_D_HIGH_ALTITUDE_MAX_HAL) / (EN_ROUTE_MAX_AGL - A_D_HIGH_ALTITUDE_MAX_AGL) * altitude + A_D_HIGH_ALTITUDE_MAX_HAL;
        }else {
            threshold = EN_ROUTE_MAX_HAL;
        }
        return threshold;
    }

    private double feetToMeters(double value){
        return value*0.3048;
    }
    private double metersToFeet(double value){
        return value*3.28084;
    }
    // Calculates distance between two GPS coordinates using the Haversine formula
    private double getDistanceBetweenTwoPoints (double lat, double lon, double lat2, double lon2){

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
    private double getCartesianDistanceBetweenTwoPoints (double lat, double lon, double lat2, double lon2){

        lat = Math.toRadians(lat);
        lat2 = Math.toRadians(lat2);
        lon = Math.toRadians(lon);
        lon2 = Math.toRadians(lon2);


        double distance = 0;
        distance = Math.pow(lon-lon2,2);
        distance += Math.pow(lat-lat2,2);
        distance = Math.sqrt(distance);

        return distance;
    }
}
