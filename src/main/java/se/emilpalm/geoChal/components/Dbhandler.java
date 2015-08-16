package se.emilpalm.geoChal.components;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import se.emilpalm.geoChal.helpers.Challenge;
import se.emilpalm.geoChal.helpers.Position;
import se.emilpalm.geoChal.helpers.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Query query = new Query("User");
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

    //FIXME: Kind of ugly hack that could be bad if insertion happends at the same time as getting size.
    public int getNextUserID() {
        return getUsers().size();
    }

    public void createChallenge(Challenge challenge) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        Key key = KeyFactory.createKey("challenges", challenge.getID().toString());
        Entity entity = new Entity("Challenge", key);

        if (user != null) {
            entity.setProperty("author_id", user.getUserId());
            entity.setProperty("author_email", user.getEmail());
            System.err.println("HELLO::::" + user.getUserId() + user.getEmail());
        }

        entity.setProperty("creatorUser", challenge.getCreatorUser());
        entity.setProperty("challengedUser", challenge.getChallangedUser());
        entity.setProperty("id", challenge.getID());
        entity.setProperty("position", challenge.getPosition());

        dss.put(entity);

    }

    private List<Challenge> getChallenges() {
        ArrayList<Challenge> challenges = new ArrayList<>();
        Query query = new Query("Challenge");
        List<Entity> entities = dss.prepare(query).asList(FetchOptions.Builder.withDefaults());
        for(Entity entity : entities) {
            challenges.add(new Challenge((String) entity.getProperty("creatorUser"), (String) entity.getProperty("challengedUser"), (UUID) entity.getProperty("id"), (Position) entity.getProperty("position")));
        }
        return challenges;
    }

    public List<Challenge> getChallengesForChallengedUser(String username) {
        ArrayList<Challenge> challenges = new ArrayList<>();
        for(Challenge challenge : getChallenges()) {
            if(challenge.getChallangedUser().equals(username)) {
                challenges.add(challenge);
            }
        }
        return challenges;
    }

    public List<Challenge> getChallengesForCreatorUser(String username) {
        ArrayList<Challenge> challenges = new ArrayList<>();
        for(Challenge challenge : getChallenges()) {
            if(challenge.getCreatorUser().equals(username)) {
                challenges.add(challenge);
            }
        }
        return challenges;
    }

    public Challenge getChallenge(UUID id) {
        for(Challenge challenge : getChallenges()) {
            if(id.equals(challenge.getID())) {
                return challenge;
            }
        }
        return null;
    }

}
