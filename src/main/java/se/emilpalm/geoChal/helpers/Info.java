package se.emilpalm.geoChal.helpers;

/**
 * Created by emil on 2015-08-23.
 */
public class Info {
    private String message;
    private int statusCode;

    public Info(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
