package se.emilpalm.geoChal.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.emilpalm.geoChal.helpers.*;

import java.util.List;

/**
 * Created by emil on 2015-07-15.
 */
@Controller
public class Challenges extends BaseComponent {

    @RequestMapping(value = "/challenge", method = RequestMethod.POST)
    public ResponseEntity<Info> newChallenge(@RequestBody ChallengeCreate challenge) {
        if(challenge == null) {
            return new ResponseEntity<Info>(new Info("Could not create a challenge with supplied data.", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
        Challenge chal = new Challenge(challenge);
        Dbhandler.getInstance().createChallenge(chal);
        return new ResponseEntity<Info>(new Info("Challenge created successfully.", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @RequestMapping(value = "/challenge/challenged/{name}", method = RequestMethod.GET)
    public ResponseEntity<ChallengeList> getChallengesForChallengedUser(@PathVariable String name) {
        if (name == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ChallengeList(Dbhandler.getInstance().getChallengesForChallengedUser(name)), HttpStatus.OK);
    }

    @RequestMapping(value = "/challenge/creator/{name}", method = RequestMethod.GET)
    public ResponseEntity<ChallengeList> getChallengesForCreatorUser(@PathVariable String name) {
        if (name == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ChallengeList(Dbhandler.getInstance().getChallengesForCreatorUser(name)), HttpStatus.OK);
    }

    @RequestMapping(value = "/challenge/{id}", method = RequestMethod.GET)
    public ResponseEntity<Challenge> getChallengeInfo(@PathVariable String id) {
        Challenge c = Dbhandler.getInstance().getChallenge(id);
        if(c == null) {
            return new ResponseEntity<Challenge>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Challenge>(c, HttpStatus.OK);
    }

    @RequestMapping(value = "/challenge/{id}", method = RequestMethod.POST)
    public ResponseEntity<Info> tellLocationForChallenge(@PathVariable String id, @RequestBody Position position) {
        if(id == null || position == null) {
            return new ResponseEntity<>(new Info("Id or position not correctly given",HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
        }

        Challenge challenge = Dbhandler.getInstance().getChallenge(id);
        if(challenge == null) {
            return new ResponseEntity<>(new Info("Challenge not found.", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        } else if (challenge.getFinished() != 0) {
            return new ResponseEntity<>(new Info("Challenge already finished.", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }

        double maxDistanceToPassChallenge = 0.02; //in kilometers
        double distance = Haversine.haversine(challenge.getPosition(), position);

        if(distance < maxDistanceToPassChallenge) {

            UserData user = getStoredUser(challenge.getChallengedUser());
            user.addPoints(20);
            Dbhandler.getInstance().createUser(user);
            challenge.setFinished(1);
            Dbhandler.getInstance().createChallenge(challenge);

            return new ResponseEntity<>(new Info("Challenge succeeded, well done!", HttpStatus.OK.value()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Info("Challenge not done. Not to close to the position.", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
