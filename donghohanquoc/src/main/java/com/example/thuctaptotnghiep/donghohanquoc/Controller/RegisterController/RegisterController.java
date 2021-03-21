package com.example.thuctaptotnghiep.donghohanquoc.Controller.RegisterController;

import com.example.thuctaptotnghiep.donghohanquoc.Common.PageConstant;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.BrandOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.CategoriesOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Service.BrandService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CategoriesService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/dangky")
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoriesService categoriesService;
    @GetMapping
    public String pageRegister(Model model, HttpSession session, HttpServletRequest request) {
        //
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        model.addAttribute("brandlist", brandOutputs);
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

        model.addAttribute("userInput",new UserInput());
        //
        session.removeAttribute("userInput");
        model.addAttribute("error", null);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    model.addAttribute("email", cookie.getValue());
                }
                if (cookie.getName().equals("password")) {
                    model.addAttribute("password", cookie.getValue());
                }
                if (cookie.getName().equals("remember")) {
                    model.addAttribute("remember", cookie.getValue());
                }
            }
        }
        return PageConstant.PAGE_REGISTER;
    }

    @PostMapping
    public String register(Model model, @ModelAttribute("userInput") UserInput user) {
        return userService.register(model, user);
    }
}
