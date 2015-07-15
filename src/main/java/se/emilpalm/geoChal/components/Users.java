package se.emilpalm.geoChal.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.emilpalm.geoChal.helpers.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by emil on 2015-07-15.
 */
@Controller
public class Users {


    private static Map<String, User> users = new ConcurrentHashMap<>();

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable String name) {
        User user = users.get(name);
        if(user == null) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<User>(users.get(name), HttpStatus.OK);
    }

    public static User getStoredUser(String username) {
        return users.get(username);
    }
}
