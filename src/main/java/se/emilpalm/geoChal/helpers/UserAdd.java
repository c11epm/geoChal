package se.emilpalm.geoChal.helpers;

/**
 * UserAdd
 * Data placeholder class to parse the create user request
 *
 * Created by emil on 2015-09-13.
 */
public class UserAdd {
    private String user;
    private String add;

    public UserAdd() {}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }
}
