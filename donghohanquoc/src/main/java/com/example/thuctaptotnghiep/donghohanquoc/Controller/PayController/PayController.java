package com.example.thuctaptotnghiep.donghohanquoc.Controller.PayController;


import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ReceiverInFor;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.BrandOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.CategoriesOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Service.BrandService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CategoriesService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("pay")
public class PayController {
    @Autowired
    private PayService payService;
    @Autowired
    private BrandService brandService;
    @Autowired
    CategoriesService categoriesService;
    @GetMapping
    public String pagePay(HttpSession session, Model model) {
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        model.addAttribute("brandlist", brandOutputs);
        return payService.pagePay(session, model);
    }
    @PostMapping
    public String pay(HttpSession session, @ModelAttribute("user") ReceiverInFor input) {
        return payService.pay(session, input);
    }
}
