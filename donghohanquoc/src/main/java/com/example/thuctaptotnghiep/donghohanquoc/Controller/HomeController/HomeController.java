package com.example.thuctaptotnghiep.donghohanquoc.Controller.HomeController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.LoginInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.*;
import com.example.thuctaptotnghiep.donghohanquoc.Service.BrandService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CategoriesService;
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

import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {
    /*@Value("${error.message}")
    private String errorMessage;*/
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    CategoriesService categoriesService;

    @GetMapping("/home")
    public String home(Model model) {
        List<ProductOutput> productOutputList = productService.getListProduct();
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        List<CategoriesOutput> listnam = new LinkedList<>();
        List<CategoriesOutput> listnu = new LinkedList<>();
        for (CategoriesOutput item : categoriesOutputList) {
            if (item.getIsformen() == 1) {
                listnam.add(item);
                model.addAttribute("listnam", listnam);
            } else {
                listnu.add(item);
                model.addAttribute("listnu", listnu);
            }
        }
        model.addAttribute("brandlist", brandOutputs);
        model.addAttribute("productInfo", productOutputList);

        return "index-3";
    }

    @GetMapping("/login-register")
    public String login(Model model) {
        model.addAttribute("userInput", new UserInput());
        model.addAttribute("loginInput", new LoginInput());
        return "login-register";
    }

    @PostMapping("/login/user")
    public String showhome(@ModelAttribute("loginInput") LoginInput loginInput, Model model) {
        System.out.println(loginInput);
        UserOutput userOutput = userService.checkLogin(loginInput);

        System.out.println(userOutput);
        if (userOutput.getEmail() == null) {
            model.addAttribute("errorMessage", "Email không tồn tại trong hệ thống");
            model.addAttribute("userInput", new UserInput());
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
    @GetMapping("/contact")
    public  String contact()
    {
        return "contact";
    }
}


