package com.example.thuctaptotnghiep.donghohanquoc.Controller.BrandController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.BrandOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Service.BrandService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BrandController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @GetMapping("/brand")
    public String getformbrand(Model model)
    {
        List<ProductOutput> productOutputList= productService.getListProduct();
        List<BrandOutput> brandOutputs= brandService.getlistbrand();
        model.addAttribute("brandlist",brandOutputs);
        model.addAttribute("productlist",productOutputList);
        return "shop";
    }


}
