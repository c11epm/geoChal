package se.emilpalm.geoChal.helpers;

/**
 * Position
 * Data placeholder class to hold a position object with longitude - latitude format.
 * Created by emil on 2015-07-16.
 */
public class Position {
    private double longitude;
    private double latitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
