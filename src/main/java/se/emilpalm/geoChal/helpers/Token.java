package se.emilpalm.geoChal.helpers;

import com.google.gson.annotations.Expose;

import java.util.UUID;

/**
 * Created by emil on 2015-07-15.
 */
public class Token {

    private UUID token;

    private long expireTime;

    private String username;


    public Token(String username) {
        this.username = username;
        token = UUID.randomUUID();
        expireTime = System.currentTimeMillis() + Constants.THREE_HOURS_MILLIS; //Current time + 3 hours.
    }

    public boolean isValid() {
        return System.currentTimeMillis() < expireTime;
    }

    public void reNew() {
        this.expireTime = System.currentTimeMillis() + Constants.THREE_HOURS_MILLIS;
    }

    public Token(UUID token, long expireTime, String username) {
        this.token = token;
        this.expireTime = expireTime;
        this.username = username;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getToken() {

        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
