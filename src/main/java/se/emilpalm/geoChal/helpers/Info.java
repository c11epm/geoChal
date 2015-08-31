package se.emilpalm.geoChal.helpers;

/**
 * Created by emil on 2015-08-23.
 */
public class Info {
    private String message;
    private int status;

    public Info(String message, int statusCode) {
        this.message = message;
        this.status = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return status;
    }

    public void setStatusCode(int statusCode) {
        this.status = statusCode;
    }
}
