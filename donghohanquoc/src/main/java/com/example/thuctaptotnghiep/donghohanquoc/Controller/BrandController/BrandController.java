package com.example.thuctaptotnghiep.donghohanquoc.Controller.BrandController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
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
    @GetMapping("/brand")
    public String getformbrand(Model model)
    {
        List<ProductOutput> productOutputList= productService.getListProduct();
        model.addAttribute("productlist",productOutputList);
        return "shop";
    }
}
