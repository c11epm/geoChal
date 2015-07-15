package se.emilpalm.geoChal.components;

import se.emilpalm.geoChal.helpers.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.emilpalm.geoChal.helpers.Token;
import se.emilpalm.geoChal.helpers.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by emil on 2015-07-14.
 */
@Controller
public class Authentication {

    private Map<String, Token> tokens = new ConcurrentHashMap<>();

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<Token> login(@RequestBody Login login) {

        if(login != null) {
            User u = Users.getStoredUser(login.getUsername());
            if(u != null && u.getPassword().equals(login.getPassword()) && u.getUsername().equals(login.getUsername())) {
                if(tokens.get(u.getUsername()).isValid()) {
                    return new ResponseEntity<Token>(tokens.get(u.getUsername()), HttpStatus.OK);
                }
                tokens.put(u.getUsername(), new Token(u.getUsername()));

                return new ResponseEntity<Token>(tokens.get(u.getUsername()), HttpStatus.OK);
            }
        }

        return new ResponseEntity<Token>(HttpStatus.BAD_REQUEST);
    }

}
