package com.example.thuctaptotnghiep.donghohanquoc.Service;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public interface ProductService {
    Page<ProductEntity> getListProduct(Integer status, Pageable pageable, Model model);
    ProductEntity getProductEntity(Integer productid);
}
