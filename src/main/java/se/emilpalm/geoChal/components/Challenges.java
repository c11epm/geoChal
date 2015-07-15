package se.emilpalm.geoChal.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.emilpalm.geoChal.helpers.Challenge;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by emil on 2015-07-15.
 */
@Controller
public class Challenges {

    private static Map<String, Challenge> challengeStore = new ConcurrentHashMap<>();



    @RequestMapping(value = "/challenge", method = RequestMethod.POST)
    public ResponseEntity<String> newChallenge(@RequestBody Challenge challenge) {

        challengeStore.put(challenge.getID(), challenge);

        return new ResponseEntity<String>("Challenge created.", HttpStatus.OK);
    }
}
