package se.emilpalm.geoChal.components;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import se.emilpalm.geoChal.helpers.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emil on 2015-08-12.
 */
public class Dbhandler {
    private static Dbhandler dbhandler = null;
    private static DatastoreService dss = null;

    protected Dbhandler() {
    }

    public static Dbhandler getInstance() {
        if (dbhandler == null) {
            dbhandler = new Dbhandler();
            dss = DatastoreServiceFactory.getDatastoreService();
            // dbhandler.connect();
        }
        return dbhandler;
    }

    public void createUser(UserData newUser) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        Key key = KeyFactory.createKey("users", newUser.getUsername());
        Entity entity = new Entity("User", key);

        if (user != null) {
            entity.setProperty("author_id", user.getUserId());
            entity.setProperty("author_email", user.getEmail());
            System.err.println("HELLO::::" + user.getUserId() + user.getEmail());
        }

        entity.setProperty("username", newUser.getUsername());
        entity.setProperty("password", newUser.getPassword());
        entity.setProperty("id", newUser.getId());
        entity.setProperty("points", newUser.getPoints());
        entity.setProperty("friends", newUser.getFriends());

        dss.put(entity);

    }

    public void updateUser(UserData user) {
        //TODO Remove old userData object and replace with new one, through createUser?
    }

    private List<UserData> getUsers() {
        ArrayList<UserData> users = new ArrayList<>();
        Query query = new Query("UserData");
        List<Entity> entities = dss.prepare(query).asList(FetchOptions.Builder.withDefaults());
        for(Entity entity : entities) {
            users.add(new UserData((String)entity.getProperty("username"), (String)entity.getProperty("password"), (int)entity.getProperty("id"), (int)entity.getProperty("points"), (List<UserData>)entity.getProperty("friends")));
        }
        return users;
    }

    public UserData getUser(String username) {
        for(UserData user : getUsers()) {
            if(username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

}
