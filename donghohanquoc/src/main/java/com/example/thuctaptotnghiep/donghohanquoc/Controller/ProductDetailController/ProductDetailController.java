package com.example.thuctaptotnghiep.donghohanquoc.Controller.ProductDetailController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.BrandOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.CategoriesOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Service.BrandService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CategoriesService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductDetailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    BrandService brandService;
    @SneakyThrows
    @GetMapping("product-details/{productid}")
    public String getDetailProduct(@PathVariable("productid") Integer productid, Model model)
    {
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        ProductOutput productOutput= productDetailService.getDetailProduct(productid);
        model.addAttribute("productdetai",productOutput);
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
        return "product-details";
    }
}
