package com.example.thuctaptotnghiep.donghohanquoc.Service;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    ResponseData<List<ProductOutput>>  getListProduct();
    ResponseData<Integer> createProductByAdmin(ProductInput productInput);
}
