package se.emilpalm.geoChal.helpers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * UserData
 * Contains all the information about a user in the system.
 *
 * Created by emil on 2015-07-15.
 */
public class UserData {
    private String username;
    private String password;
    private long points;
    private ArrayList<String> friends;

    public UserData() {

    }

    public UserData(String username, String password) {
        this.username = username;
        this.password = password;
        points = 0;
        friends = new ArrayList<>();
    }

    public UserData(String username, String password, long points, ArrayList<String> friends) {
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

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public void addPoints(int i) {
        points += i;
    }

    public void addFriend(String add) {
        if(friends == null) {
            friends = new ArrayList<>();
        }
        friends.add(add);
    }
}
