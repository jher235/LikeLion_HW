package org.mjulikelion.liikelion12thhw.week3.controller;

import lombok.AllArgsConstructor;
import org.mjulikelion.liikelion12thhw.week3.User;
import org.mjulikelion.liikelion12thhw.week3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }







}
