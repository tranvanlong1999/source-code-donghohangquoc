package com.example.thuctaptotnghiep.donghohanquoc.Controller.UserController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/taikhoan")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/taotaikhoanboiadmin")
    public String createUserByAdmin(Model model, @ModelAttribute("userInput") UserInput input) {
        userService.createUserByAdmin(model,input);
        return "";
    }

    @GetMapping("/danhsachtaikhoan")
    public String getListUser() {
        //model.addAttribute("danhsachtaikhoan", userService.getListUser());
        userService.getListUser();
        return "ok";
    }
     /*
     @DeleteMapping
     public ResponseData<Boolean> deleteUserById(@RequestBody Integer id)
     {
          return userService.deleteUserById(id);
     }
     @PutMapping
     public ResponseData<Boolean> updateUserByAdmin(@RequestBody UserUpdateInput userUpdateInput)
     {
          return userService.updateUserByAdmin(userUpdateInput);
     }*/
}
