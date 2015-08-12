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
public class Users {


    private static Map<String, UserData> users = new ConcurrentHashMap<>();
    private static int count = 0;

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public ResponseEntity<UserData> getUser(@PathVariable String name) {
        UserData user = Dbhandler.getInstance().getUser(name);
        if(user == null) {
            return new ResponseEntity<UserData>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserData>(users.get(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> newUser(@RequestBody Login login) {
        if(users.containsKey(login.getUsername())) {
            return new ResponseEntity<String>("UserData already registered.", HttpStatus.BAD_REQUEST);
        }
        UserData newUser = new UserData(login.getUsername(), login.getPassword(), ++count);
        Dbhandler.getInstance().createUser(newUser);
        String response = "User: " + newUser.getUsername() + " created successfully";

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public static UserData getStoredUser(String username) {
        return users.get(username);
    }
}
