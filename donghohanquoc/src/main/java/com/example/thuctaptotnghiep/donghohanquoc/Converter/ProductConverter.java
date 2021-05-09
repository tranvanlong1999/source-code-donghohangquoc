package com.example.thuctaptotnghiep.donghohanquoc.Converter;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;




@Component
public class ProductConverter {
    public ProductOutput toProductEntity(ProductEntity productEntity)
    {
        ProductOutput productOutput = new ProductOutput();
        if(!ObjectUtils.isEmpty(productEntity))
        {
            productOutput.setId(productEntity.getId());
            productOutput.setProductname(productEntity.getProductname());
            productOutput.setDescription(productEntity.getDescription());
            productOutput.setBrandid(productEntity.getBrandentity().getId());
            productOutput.setCategoryid(productEntity.getCategory().getId());
            productOutput.setPriceStr(productEntity.getPriceStr());
            productOutput.setCreateAt(productEntity.getCreatedat());
            productOutput.setStatus(productEntity.getStatus());
            productOutput.setImage(productEntity.getImage());
            productOutput.setBrandname(productEntity.getBrandentity().getName());
            productOutput.setCategoryname(productEntity.getCategory().getName());
            productOutput.setPrice(productEntity.getPrice());
        }
        return productOutput;
    }

}
