package se.emilpalm.geoChal;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * Application
 * The application class, runs component scan to find the other API end-points.
 *
 * Created by emil on 2015-07-14.
 */

@Configuration
@Controller
@ComponentScan
public class Application {


}
