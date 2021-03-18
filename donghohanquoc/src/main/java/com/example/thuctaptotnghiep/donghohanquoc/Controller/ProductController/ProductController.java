package com.example.thuctaptotnghiep.donghohanquoc.Controller.ProductController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    ResponseData<List<ProductOutput>> getListProduct() {
        /*return productService.getListProduct();*/
        return null;
    }

    @PostMapping
    ResponseData<Integer> createProductByAdmin(@RequestBody ProductInput productInput) {
        return productService.createProductByAdmin(productInput);
    }

    @DeleteMapping("delete/{id}")
    ResponseData<Boolean> deleteProductByAdmin(@PathVariable("id") Integer productid) {
        return productService.deleteProductByAdmin(productid);
    }

    @PutMapping
    ResponseData<Boolean> updateProductByAdmin(@RequestBody ProductUpdateInput productUpdateInput) {
        return null;
    }

    @PostMapping("private/create/images/{productId}")
    public Boolean createImagesInProduct(@RequestPart("files") MultipartFile files,
                                         @PathVariable Integer productId) {
        return productService.createImagesInProduct(files, productId);
    }

}

