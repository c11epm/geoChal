package se.emilpalm.geoChal.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.emilpalm.geoChal.helpers.Challenge;
import se.emilpalm.geoChal.helpers.Haversine;
import se.emilpalm.geoChal.helpers.Position;
import se.emilpalm.geoChal.helpers.UserData;

import java.util.List;

/**
 * Created by emil on 2015-07-15.
 */
@Controller
public class Challenges extends BaseComponent {

    @RequestMapping(value = "/challenge", method = RequestMethod.POST)
    public ResponseEntity<String> newChallenge(@RequestBody Challenge challenge) {
        if(challenge == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Dbhandler.getInstance().createChallenge(challenge);
        return new ResponseEntity<String>("Challenge created.", HttpStatus.OK);
    }

    @RequestMapping(value = "/challenge/challenged/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Challenge>> getChallengesForChallengedUser(@PathVariable String name) {
        if (name == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Dbhandler.getInstance().getChallengesForChallengedUser(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/challenge/creator/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Challenge>> getChallengesForCreatorUser(@PathVariable String name) {
        if (name == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Dbhandler.getInstance().getChallengesForCreatorUser(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/challenge/location/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> tellLocationForChallenge(@PathVariable String id, @RequestBody Position position) {
        if(id == null || position == null) {
            return new ResponseEntity<>("Id or position not correctly given",HttpStatus.BAD_REQUEST);
        }

        Challenge challenge = Dbhandler.getInstance().getChallenge(id);
        if(challenge == null) {
            return new ResponseEntity<>("Challenge not found.", HttpStatus.BAD_REQUEST);
        }

        double maxDistanceToPassChallenge = 0.02; //in kilometers
        double distance = Haversine.haversine(challenge.getPosition(), position);

        System.err.println("CHALLENGE: " + challenge.getLatitude() + " long: " + challenge.getLongitude());
        System.err.println("POSITION: " + position.getLatitude() + " long: " + position.getLongitude());

        if(distance < maxDistanceToPassChallenge) {

            UserData user = getStoredUser(challenge.getChallengedUser());
            user.addPoints(20);
            Dbhandler.getInstance().createUser(user);

            //TODO Give points, remove/set as succeeded challenge.
            return new ResponseEntity<>("Challenge succeeded, well done!", HttpStatus.OK);
        }
        System.err.println("DISTANCE: " + distance);
        //FIXME
        return new ResponseEntity<String>("Challenge not done. Not to close to the position.", HttpStatus.BAD_REQUEST);
    }
}
