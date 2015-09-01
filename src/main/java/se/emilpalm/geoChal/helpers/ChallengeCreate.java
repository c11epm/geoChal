package se.emilpalm.geoChal.helpers;

/**
 * Created by emil on 2015-09-01.
 */
public class ChallengeCreate {
    private String creatorUser;
    private String challengedUser;

    private double longitude;
    private double latitude;

    public ChallengeCreate(String creatorUser, String challengedUser, double longitude, double latitude) {
        this.creatorUser = creatorUser;
        this.challengedUser = challengedUser;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getChallengedUser() {
        return challengedUser;
    }

    public void setChallengedUser(String challengedUser) {
        this.challengedUser = challengedUser;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
