package se.emilpalm.geoChal.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.emilpalm.geoChal.helpers.*;

import javax.xml.ws.http.HTTPBinding;

/**
 * Created by emil on 2015-07-15.
 */
@Controller
public class Users extends BaseComponent {

    //TODO not return passwords...
    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public ResponseEntity<UserInfo> getUser(@PathVariable String name) {
        UserData user = getStoredUser(name);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserInfo info = new UserInfo();
        info.setUsername(user.getUsername());
        info.setFriends(user.getFriends());
        info.setPoints(user.getPoints());

        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    //Create new user
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Info> newUser(@RequestBody Login login) {
        UserData user = getStoredUser(login.getUsername());
        if(user != null) {
            //If user already exists
            return new ResponseEntity<Info>(new Info(login.getUsername() +" already registered.", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
        //Else, create a new user and add to db
        UserData newUser = new UserData(login.getUsername(), login.getPassword());
        Dbhandler.getInstance().createUser(newUser);
        String response = "User: \'" + newUser.getUsername() + "\' created successfully";

        return new ResponseEntity<Info>(new Info(response, HttpStatus.OK.value()), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResponseEntity<Info> addUser(@RequestBody UserAdd userAdd) {

        Info info;
        UserData u = getStoredUser(userAdd.getUser());
        UserData a = getStoredUser(userAdd.getAdd());

        if(u == null) {
            info = new Info("Your username does not exist", HttpStatus.BAD_REQUEST.value());
        } else if(a == null) {
            info = new Info("User '" + userAdd.getAdd() + "' does not exist", HttpStatus.BAD_REQUEST.value());
        } else {
            u.addFriend(a.getUsername());
            Dbhandler.getInstance().createUser(u);
            info = new Info("User '" + userAdd.getAdd() + "' added to your friend list", HttpStatus.OK.value());
        }
        return new ResponseEntity<Info>(info, info.getStatusCode() == 200 ?
                HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
