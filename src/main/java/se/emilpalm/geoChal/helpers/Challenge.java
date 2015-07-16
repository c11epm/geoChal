package se.emilpalm.geoChal.helpers;

/**
 * Created by emil on 2015-07-15.
 */
public class Challenge {

    private String title;
    private String creatorUser;
    private String challangedUser;
    private String ID;

    private Position position;
    //GPS coordinates

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "title='" + title + '\'' +
                ", creatorUser='" + creatorUser + '\'' +
                ", challangedUser='" + challangedUser + '\'' +
                ", ID='" + ID + '\'' +
                ", position=" + position +
                '}';
    }
}
