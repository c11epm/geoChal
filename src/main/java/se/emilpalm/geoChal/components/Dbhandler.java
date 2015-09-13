package se.emilpalm.geoChal.components;

import com.google.appengine.api.datastore.*;

import se.emilpalm.geoChal.helpers.Challenge;
import se.emilpalm.geoChal.helpers.UserData;

import java.io.Serializable;
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
        //Key key = KeyFactory.createKey("users", newUser.getUsername());
        Entity entity = new Entity("User", newUser.getUsername());

        /*try {
            Entity e = dss.get(key);
            dss.delete(key);
        } catch (EntityNotFoundException e1) {
            //Ok, since the entity was not found.
        }*/

        entity.setProperty("username", newUser.getUsername());
        entity.setProperty("password", newUser.getPassword());
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
            users.add(new UserData((String)entity.getProperty("username"), (String)entity.getProperty("password"), (long)entity.getProperty("points"), (ArrayList<String>)entity.getProperty("friends")));
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

    public void createChallenge(Challenge challenge) {
        //Key key = KeyFactory.createKey("challenges", challenge.getID());
        Entity entity = new Entity("Challenge", challenge.getID());

        /*try {
            Entity e = dss.get(key);
            dss.delete(key);
        } catch (EntityNotFoundException e1) {
            //Ok, since the entity was not found.
        }*/

        entity.setProperty("creatorUser", challenge.getCreatorUser());
        entity.setProperty("challengedUser", challenge.getChallengedUser());
        entity.setProperty("id", challenge.getID());
        entity.setProperty("latitude", challenge.getLatitude());
        entity.setProperty("longitude", challenge.getLongitude());
        entity.setProperty("finished", challenge.getFinished());

        dss.put(entity);
    }

    private List<Challenge> getChallenges() {
        ArrayList<Challenge> challenges = new ArrayList<>();
        Query query = new Query("Challenge");
        List<Entity> entities = dss.prepare(query).asList(FetchOptions.Builder.withDefaults());
        for(Entity entity : entities) {
            challenges.add(new Challenge((String) entity.getProperty("creatorUser"),
                    (String) entity.getProperty("challengedUser"),
                    (String) entity.getProperty("id"),
                    (double) entity.getProperty("latitude"),
                    (double) entity.getProperty("longitude"),
                    (long) entity.getProperty("finished")));
        }
        return challenges;
    }

    public List<Challenge> getChallengesForChallengedUser(String username) {
        ArrayList<Challenge> challenges = new ArrayList<>();
        for(Challenge challenge : getChallenges()) {
            if(challenge.getChallengedUser().equals(username)) {
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

    public Challenge getChallenge(String id) {
        for(Challenge challenge : getChallenges()) {
            if(id.equals(challenge.getID())) {
                return challenge;
            }
        }
        return null;
    }

}
