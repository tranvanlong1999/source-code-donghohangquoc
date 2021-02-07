package com.example.thuctaptotnghiep.donghohanquoc.Controller.UserController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
     @Autowired
     private UserService userService;
     @PostMapping("/login")
     public ResponseEntity<?> login(@RequestBody UserInput input)
     {
          UserOutput userOutput= userService.checkLogin(input);
          return ResponseEntity.status(HttpStatus.OK).body(userOutput);
     }
     @PostMapping
     public ResponseEntity<?>  createUser(@RequestBody UserInput input)
     {
          return  ResponseEntity.status(HttpStatus.OK).body(userService.SaveUser(input));
     }
     @GetMapping
     public ResponseEntity<?> getListUser()
     {
          List<UserOutput> userOutputList= userService.getListUser();
          return ResponseEntity.status(HttpStatus.OK).body(userOutputList);
     }
     @DeleteMapping
     public ResponseEntity<?> deleteUserById(@RequestBody Integer id)
     {
          return null;
     }
}
