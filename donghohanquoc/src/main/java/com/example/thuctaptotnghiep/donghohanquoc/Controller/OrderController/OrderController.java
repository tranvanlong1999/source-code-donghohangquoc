package com.example.thuctaptotnghiep.donghohanquoc.Controller.OrderController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.BrandOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.CategoriesOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Service.BrandService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private BrandService brandService;
    @Autowired
    CategoriesService categoriesService;

    @GetMapping("/order")
    public String getListOrder(Model model) {

        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        model.addAttribute("brandlist", brandOutputs);
        return "cart-page";
    }


}
