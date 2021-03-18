package com.example.thuctaptotnghiep.donghohanquoc.Service;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@Service
public interface ProductService {
    List<ProductOutput>  getListProduct();
    ResponseData<Integer> createProductByAdmin(ProductInput productInput);
    ResponseData<Boolean> deleteProductByAdmin(Integer productid);
    ResponseData<Boolean> updateProductByAdmin(ProductUpdateInput productUpdateInput);
    Boolean createImagesInProduct(MultipartFile files, Integer productId);
}
