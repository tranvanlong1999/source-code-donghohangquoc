package com.example.thuctaptotnghiep.donghohanquoc.Controller.ProductController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping
    ResponseData<List<ProductOutput>> getListProduct()
    {
        return productService.getListProduct();
    }
    @PostMapping
    ResponseData <Integer> createProductByAdmin(@RequestBody ProductInput productInput)
    {
        return productService.createProductByAdmin(productInput) ;
    }
    @DeleteMapping("delete/{id}")
    ResponseData<Boolean> deleteProductByAdmin(@PathVariable("id") Integer productid)
    {
        return productService.deleteProductByAdmin(productid);
    }
    @PutMapping
    ResponseData<Boolean> updateProductByAdmin(@RequestBody ProductUpdateInput productUpdateInput)
    {
        return null;
    }
}
