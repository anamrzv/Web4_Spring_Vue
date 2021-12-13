package application.controller;

import application.domain.Point;
import application.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/points")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private PointRepository pointRepository;

    @GetMapping("/")
    public ResponseEntity<Point> show(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

