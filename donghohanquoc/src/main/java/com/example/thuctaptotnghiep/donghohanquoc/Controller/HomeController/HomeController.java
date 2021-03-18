package com.example.thuctaptotnghiep.donghohanquoc.Controller.HomeController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.LoginInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class HomeController {
    /*@Value("${error.message}")
    private String errorMessage;*/


    private  String errorMessage="Email không tồn tại";
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String home(Model model) {
        List<ProductOutput> productOutputList = productService.getListProduct();
        model.addAttribute("productInfo", productOutputList);
        return "index-3";
    }

    @GetMapping("/login-register")
    public String login(Model model){
        LoginInput loginInput = new LoginInput();
        UserInput userInput = new UserInput();
        model.addAttribute("errorMessage","");
        model.addAttribute("userInput", userInput);
        model.addAttribute("loginInput", loginInput);
        return "login-register";
    }

    @PostMapping("/login/user")
    public String showhome(@ModelAttribute("loginInput") LoginInput loginInput, Model model) {
        UserOutput userOutput = userService.checkLogin(loginInput);
        System.out.println(loginInput);
        if (userOutput.getEmail() == null) {
            model.addAttribute("errorMessage",errorMessage);
            return "redirect:/login-register";
        } else {
            return "redirect:/home";
        }

    }

    @PostMapping("/register/user/bycustomer")
    public String registeruser(@ModelAttribute("userInput") UserInput userInput) {
        userService.createUserByAdmin(userInput);
        return "redirect:/login-register";
    }
}

