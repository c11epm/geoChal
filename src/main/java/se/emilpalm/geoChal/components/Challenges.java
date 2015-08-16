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

import java.util.List;
import java.util.UUID;

/**
 * Created by emil on 2015-07-15.
 */
@Controller
public class Challenges {

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
    public ResponseEntity<String> tellLocationForChallenge(@PathVariable String id, @RequestBody Position poistion) {
        if(id == null || poistion == null) {
            return new ResponseEntity<>("Id or position not correctly given",HttpStatus.BAD_REQUEST);
        }

        Challenge challenge = Dbhandler.getInstance().getChallenge(UUID.fromString(id));
        if(challenge == null) {
            return new ResponseEntity<>("Challenge not found.", HttpStatus.BAD_REQUEST);
        }

        double maxDistanceToPassChallenge = 0.02; //in kilometers

        if(Haversine.haversine(challenge.getPosition(), poistion) < maxDistanceToPassChallenge) {
            //TODO Give points, remove/set as succeeded challenge.
            return new ResponseEntity<>("Challenge succeeded, well done!", HttpStatus.OK);
        }

        //FIXME
        return new ResponseEntity<String>("Challenge not done. Not to close to the position.", HttpStatus.BAD_REQUEST);
    }
}
