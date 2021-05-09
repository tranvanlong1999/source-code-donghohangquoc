package com.example.thuctaptotnghiep.donghohanquoc.Controller.ProductDetailController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ColorEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductAtributeEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.SizeEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductAtributeInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.BrandOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.CategoriesOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.ColorRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.ProductAtributeRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.ProductRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.SizeRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Service.BrandService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CategoriesService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductDetailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
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
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    ProductAtributeRepository productAtributeRepository;
    @Autowired
    ProductRepository  productRepository;
    @SneakyThrows
    @GetMapping("product-details/{productid}")
    public String getDetailProduct(@PathVariable("productid") Integer productid, Model model)
    {
        ProductAtributeInput productAtributeInput= new ProductAtributeInput();
        model.addAttribute("productAtributeInput",productAtributeInput);
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        ProductOutput productOutput= productDetailService.getDetailProduct(productid);
        HashMap<Integer, String> listcolorsize= new HashMap<Integer,String>();
        List<ProductAtributeEntity> listproductdetail= productAtributeRepository.findByProductId(productid);
        for (ProductAtributeEntity info: listproductdetail)  {
            if(info.getProductentity().getId() == productid)
            {
                Integer idDetail=info.getId();
                String colorSizeName="Màu: "+info.getColorentity().getName()+"/cỡ : "+info.getSizeentity().getName();
                listcolorsize.put(idDetail,colorSizeName);
            }
        }
        model.addAttribute("listcolorsize",listcolorsize);
        model.addAttribute("productdetai",productOutput);
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        model.addAttribute("brandlist", brandOutputs);
        List<ProductEntity> listProductOfBrand= new LinkedList<>();
        List<ProductEntity> listEntity= productRepository.findAll();
        for (ProductEntity infor : listEntity) {
            if(infor.getBrandentity().getId()==productOutput.getBrandid())
                listProductOfBrand.add(infor);
        }
        model.addAttribute("listProductOfBrand",listProductOfBrand);
        return "product-details";
    }
}
