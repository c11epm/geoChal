package se.emilpalm.geoChal.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emil on 2015-09-13.
 */
public class UserInfo {
    private String username;
    private long points;
    private ArrayList<String> friends = null;

    public UserInfo() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = new ArrayList<>();
        if(friends == null) {
            return;
        }
        for(String user : friends) {
            this.friends.add(user);
        }
    }
}
