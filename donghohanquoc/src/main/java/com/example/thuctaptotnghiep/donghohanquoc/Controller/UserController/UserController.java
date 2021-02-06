package com.example.thuctaptotnghiep.donghohanquoc.Controller.UserController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
     @Autowired
     private UserService userService;
     @PostMapping
     public ResponseEntity<?> login(@RequestBody UserInput model)
     {
          UserOutput userOutput= userService.checkLogin(model);
          return ResponseEntity.status(HttpStatus.OK).body(userOutput);
     }
}
