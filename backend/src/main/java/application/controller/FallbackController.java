package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FallbackController {
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.OPTIONS}, path = {"/api/points", "/api/points/main"})
    public String forwardPaths() {
        return "forward:/index.html";
    }
}
