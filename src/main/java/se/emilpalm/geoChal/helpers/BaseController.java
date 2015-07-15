package se.emilpalm.geoChal.helpers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by emil on 2015-03-07.
 */
public abstract class BaseController {

    protected ResponseEntity<String> generateResponse(HttpServletResponse response, String body, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        response.setContentType("text/plain"); // set the content type
        return new ResponseEntity<String>(body, headers, status);
    }

}
