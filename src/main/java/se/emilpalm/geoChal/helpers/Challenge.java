package se.emilpalm.geoChal.helpers;

import java.util.UUID;

/**
 * Created by emil on 2015-07-15.
 */
public class Challenge {

    private String creatorUser;
    private String challangedUser;
    private UUID id;

    private Position position;
    //GPS coordinates

    public Challenge(String creatorUser, String challangedUser, UUID id, Position position) {
        this.creatorUser = creatorUser;
        this.challangedUser = challangedUser;
        this.id = id;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getChallangedUser() {
        return challangedUser;
    }

    public void setChallangedUser(String challangedUser) {
        this.challangedUser = challangedUser;
    }

    public UUID getID() {
        return id;
    }

    public void setID(UUID id) {
        this.id = id;
    }

}
