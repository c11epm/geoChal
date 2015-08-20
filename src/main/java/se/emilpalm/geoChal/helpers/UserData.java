package se.emilpalm.geoChal.helpers;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by emil on 2015-07-15.
 */
public class UserData {
    private String username;
    private String password;
    private long points;
    private List<UserData> friends;

    public UserData() {

    }

    public UserData(String username, String password) {
        this.username = username;
        this.password = password;
        points = 0;
        friends = new LinkedList<>();
    }

    public UserData(String username, String password, long points, List<UserData> friends) {
        this.username = username;
        this.password = password;
        this.points = points;
        this.friends = friends;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UserData) {
            UserData u = (UserData) obj;
            return u.username.equals(this.username);
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<UserData> getFriends() {
        return friends;
    }

    public void setFriends(List<UserData> friends) {
        this.friends = friends;
    }

    public void addPoints(int i) {
        points += i;
    }
}
