package se.emilpalm.geoChal.helpers;

import java.util.UUID;

/**
 * Challenge
 * Holds the info about a challenge.
 *
 * Created by emil on 2015-07-15.
 */
public class Challenge {
    //TODO add expire time and created time

    private String creatorUser;
    private String challengedUser;
    private String id;

    //GPS coordinates

    private double longitude;
    private double latitude;

    private long finished;

    public Challenge(ChallengeCreate chal) {
        this.creatorUser = chal.getCreatorUser();
        this.challengedUser = chal.getChallengedUser();
        this.id = UUID.randomUUID().toString();
        this.longitude = chal.getLongitude();
        this.latitude = chal.getLatitude();
    }

    public Challenge(String creatorUser, String challengedUser, String id, double latitude, double longitude) {
        this.creatorUser = creatorUser;
        this.challengedUser = challengedUser;
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        finished = 0;
    }

    public Challenge(String creatorUser, String challengedUser, String id, double latitude, double longitude, long finished) {
        this.creatorUser = creatorUser;
        this.challengedUser = challengedUser;
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.finished = finished;
    }

    public Challenge() {}



    public Position getPosition() {
        Position pos = new Position();
        pos.setLatitude(latitude);
        pos.setLongitude(longitude);
        return pos;
    }

    public void setPosition(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

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

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
