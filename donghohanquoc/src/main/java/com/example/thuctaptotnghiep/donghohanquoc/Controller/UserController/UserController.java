package com.example.thuctaptotnghiep.donghohanquoc.Controller.UserController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.LoginInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
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
     public ResponseData<UserOutput> login(@RequestBody LoginInput input)
     {
        //  return userService.checkLogin(input);
          return  null;
     }
     @PostMapping
     public ResponseData<Boolean>  createUserByAdmin(@RequestBody UserInput input)
     {
          return  userService.createUserByAdmin(input);
     }
     @GetMapping
     public ResponseData<List<UserOutput>> getListUser()
     {
          return userService.getListUser();
     }
     @DeleteMapping
     public ResponseData<Boolean> deleteUserById(@RequestBody Integer id)
     {
          return userService.deleteUserById(id);
     }
     @PutMapping
     public ResponseData<Boolean> updateUserByAdmin(@RequestBody UserUpdateInput userUpdateInput)
     {
          return userService.updateUserByAdmin(userUpdateInput);
     }
}
