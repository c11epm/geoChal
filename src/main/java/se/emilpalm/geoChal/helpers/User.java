package se.emilpalm.geoChal.helpers;

import java.util.List;

/**
 * Created by emil on 2015-07-15.
 */
public class User {
    private String username;
    private String password;
    private int id;
    private int points;
    private List<User> firends;

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

    public List<User> getFirends() {
        return firends;
    }

    public void setFirends(List<User> firends) {
        this.firends = firends;
    }
}
