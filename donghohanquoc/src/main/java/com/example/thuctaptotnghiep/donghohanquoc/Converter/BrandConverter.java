package com.example.thuctaptotnghiep.donghohanquoc.Converter;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.BrandEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.CategoriesEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.CategoryInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.BrandOutput;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Component
public class BrandConverter {
    public BrandOutput toBrandEntity(BrandEntity brandEntity)
    {
        BrandOutput brandOutput = new BrandOutput();
        if(!ObjectUtils.isEmpty(brandEntity))
        {
            brandOutput.setId(brandEntity.getId());
            brandOutput.setName(brandEntity.getName());
            brandOutput.setDescription(brandEntity.getDescription());
        }
        return brandOutput;
    }
}
