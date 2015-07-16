package se.emilpalm.geoChal.helpers;

/**
 * Created by emil on 2015-07-16.
 */
public class Position {

    private double longitude;
    private double latitude;

    private double acceptMeters;

    @Override
    public String toString() {
        return "Position{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", acceptMeters=" + acceptMeters +
                '}';
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAcceptMeters() {
        return acceptMeters;
    }

    public void setAcceptMeters(double acceptMeters) {
        this.acceptMeters = acceptMeters;
    }

    public double getLongitude() {

        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
