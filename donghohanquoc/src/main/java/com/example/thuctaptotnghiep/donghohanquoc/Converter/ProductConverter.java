package com.example.thuctaptotnghiep.donghohanquoc.Converter;

import com.example.thuctaptotnghiep.donghohanquoc.Constants.Constants;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Component
public class ProductConverter {
    public ProductOutput toProductEntity(ProductEntity productEntity)
    {
        ProductOutput productOutput = new ProductOutput();
        if(!ObjectUtils.isEmpty(productEntity))
        {
            productOutput.setId(productEntity.getId());
            productOutput.setProductname(productEntity.getProductname());
            productOutput.setImage(productEntity.getImage());
            productOutput.setPrice(productEntity.getPrice());
            productOutput.setDescription(productEntity.getDescription());
            productOutput.setStatus(productEntity.getStatus());
            productOutput.setBrandentity(productEntity.getBrandentity());
            productOutput.setQuantitysold(productEntity.getQuantitysold());
            productOutput.setQuantityremaining(productEntity.getQuantityremaining());
            productOutput.setCreatedat(productEntity.getCreatedat());
            productOutput.setCreatedby(productEntity.getCreatedby());
            productOutput.setUpdatedat(productEntity.getUpdatedat());
            productOutput.setUpdatedby(productEntity.getUpdatedby());
            productOutput.setPath(productEntity.getPath());
        }
        return productOutput;
    }
    public ProductEntity toProductInput(ProductInput productInput)
    {
        ProductEntity productEntity = new ProductEntity();
        if(!ObjectUtils.isEmpty(productInput))
        {
            productEntity.setProductname(productInput.getProductname());
            productEntity.setImage(productInput.getImage());
            productEntity.setPrice(productInput.getPrice());
            productEntity.setDescription(productInput.getDescription());
            productEntity.setBrandentity(productInput.getBrandentity());
            productEntity.setQuantitysold(productInput.getQuantitysold());
            productEntity.setQuantityremaining(productInput.getQuantityremaining());
            productEntity.setCreatedby(productInput.getCreatedby());
            productEntity.setStatus(Constants.STATUS_ACTIVE);
            productEntity.setCreatedat(new Date());
        }
        return productEntity;
    }
}
