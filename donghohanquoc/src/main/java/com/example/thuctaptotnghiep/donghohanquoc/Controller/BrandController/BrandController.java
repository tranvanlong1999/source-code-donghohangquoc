package com.example.thuctaptotnghiep.donghohanquoc.Controller.BrandController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.BrandOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.CategoriesOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Service.BrandService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CategoriesService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class BrandController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    CategoriesService categoriesService;
    @GetMapping("/brand")
    public String getformbrand(Model model)
    {
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
        return "shop";
    }


}
