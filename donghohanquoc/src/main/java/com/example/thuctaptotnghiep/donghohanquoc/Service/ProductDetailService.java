package com.example.thuctaptotnghiep.donghohanquoc.Service;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import org.springframework.stereotype.Service;

@Service
public interface ProductDetailService {
    ProductOutput getDetailProduct(Integer productId) throws Exception;
}
