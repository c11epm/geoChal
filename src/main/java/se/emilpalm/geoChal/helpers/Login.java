package se.emilpalm.geoChal.helpers;


/**
 * Login
 * Data placeholder class for the API login request, used to parse the login request JSON body.
 *
 * Created by emil on 2015-07-14.
 */
public class Login {


    private String username;

    private String password;

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

}
