package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Converter.ProductConverter;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.ProductRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductRepository  productRepository;
    @Autowired
    private ProductConverter productConverter;
    @Override
    public ProductOutput getDetailProduct(Integer productId) throws Exception{
        ProductOutput productOutput= new ProductOutput();
        // san pham
        ProductEntity productEntity = productRepository.findById(productId).get();
        if(ObjectUtils.isEmpty(productEntity))
            throw new Exception("san pham không có trong cơ sở dữ liệu ");
        productOutput= productConverter.toProductEntity(productEntity);
        return productOutput;
    }
}
