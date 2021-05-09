package com.example.thuctaptotnghiep.donghohanquoc.Controller.HomeController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.SizeEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.UserEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.LoginInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.*;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.ProductRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Service.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    HomeService homeService;
    @Autowired
    UserService userService;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/home")
    public String home(Model model, HttpSession session, Pageable pageable) {
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        Page<ProductEntity> page = productRepository.findAll(
                PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdat")));
        List<SizeEntity> listsize= homeService.getListSize();
        model.addAttribute("listsize",listsize);
        model.addAttribute("productInfo", page);
        session.getAttribute("email");
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        model.addAttribute("brandlist", brandOutputs);
        return "index-3";
    }
    @GetMapping("/contact")
    public  String contact()
    {
        return "contact";
    }

    @GetMapping("logout")
    public String logout(Model model, HttpSession session, HttpServletResponse response) {
        /*return "redirect:/dang-nhap";*/
        return userService.pageLogout(model, session, response);
    }

}


