package se.emilpalm.geoChal.helpers;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by emil on 2015-07-15.
 */
public class User {
    private String username;
    private String password;
    private int id;
    private int points;
    private List<User> friends;

    public User() {

    }

    public User(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
        points = 0;
        friends = new LinkedList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User) {
            User u = (User) obj;
            return u.id == this.id && u.username.equals(this.username);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
