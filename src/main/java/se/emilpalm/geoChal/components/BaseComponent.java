package se.emilpalm.geoChal.components;

import se.emilpalm.geoChal.helpers.UserData;

/**
 * Created by emil on 2015-08-16.
 */
public abstract class BaseComponent {
    protected UserData getStoredUser(String username) {
        return Dbhandler.getInstance().getUser(username);
    }
}
