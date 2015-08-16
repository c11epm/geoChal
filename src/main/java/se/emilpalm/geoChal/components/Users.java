package se.emilpalm.geoChal.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.emilpalm.geoChal.helpers.Login;
import se.emilpalm.geoChal.helpers.UserData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by emil on 2015-07-15.
 */
@Controller
public class Users extends BaseComponent{


    private static int count = 0;

    //Get info about one user
    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public ResponseEntity<UserData> getUser(@PathVariable String name) {
        UserData user = getStoredUser(name);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Create new user
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> newUser(@RequestBody Login login) {
        UserData user = getStoredUser(login.getUsername());
        if(user != null) {
            //If user already exists
            return new ResponseEntity<>("UserData already registered.", HttpStatus.BAD_REQUEST);
        }
        //Else, create a new user and add to db
        UserData newUser = new UserData(login.getUsername(), login.getPassword(), Dbhandler.getInstance().getNextUserID());
        Dbhandler.getInstance().createUser(newUser);
        String response = "User: " + newUser.getUsername() + " created successfully";

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
