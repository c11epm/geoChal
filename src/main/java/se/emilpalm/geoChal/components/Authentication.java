package se.emilpalm.geoChal.components;

import se.emilpalm.geoChal.helpers.Info;
import se.emilpalm.geoChal.helpers.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.emilpalm.geoChal.helpers.Token;
import se.emilpalm.geoChal.helpers.UserData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Authentication class, holds the login function
 *
 * Created by emil on 2015-07-14.
 */
@Controller
public class Authentication extends BaseComponent {

    private Map<String, Token> tokens = new ConcurrentHashMap<>();

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<Token> login(@RequestBody Login login) {

        if(login != null) {
            UserData u = getStoredUser(login.getUsername());
            if(u != null && u.getPassword().equals(login.getPassword()) && u.getUsername().equals(login.getUsername())) {
                if(tokens.containsKey(u.getUsername())) {
                    if(tokens.get(u.getUsername()).isValid()) {
                        return new ResponseEntity<>(tokens.get(u.getUsername()), HttpStatus.OK);
                    } else {
                        //Delete old token
                        tokens.remove(u.getUsername());
                        //Put new one in.
                        tokens.put(u.getUsername(), new Token(u.getUsername()));
                    }
                }
                //If token not exists
                else {
                    tokens.put(u.getUsername(), new Token(u.getUsername()));
                }

                return new ResponseEntity<>(tokens.get(u.getUsername()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
