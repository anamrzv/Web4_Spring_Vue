package application.controller;

import application.configuration.jwt.JwtProvider;
import application.domain.User;
import application.pojo.UserRequest;
import application.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping(value="/auth",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<String> auth(@RequestBody UserRequest userRequest) {
        User user = userService.findByLoginAndPassword(userRequest.getLogin(), userRequest.getPassword());
        if (user != null) {
            String token = jwtProvider.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        } else return new ResponseEntity<>("Неправильный логин и/или пароль", HttpStatus.NOT_FOUND);
    }

    @PutMapping(name="/auth",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
        User user = userService.findByLogin(userRequest.getLogin());
        if (user==null) {
            userService.saveUser(userRequest.getLogin(), userRequest.getPassword());
            return ResponseEntity.ok("Вы успешно зарегистрированы");
        } else return new ResponseEntity<>("Логин уже существует", HttpStatus.BAD_REQUEST);
    }

}

