package com.example.thuctaptotnghiep.donghohanquoc.Controller.ProductDetailController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductDetailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @SneakyThrows
    @GetMapping("product-details/{productid}")
    public String getDetailProduct(@PathVariable("productid") Integer productid, Model model)
    {
        ProductOutput productOutput= productDetailService.getDetailProduct(productid);
        model.addAttribute("productdetai",productOutput);
        return "product-details";
    }
}
