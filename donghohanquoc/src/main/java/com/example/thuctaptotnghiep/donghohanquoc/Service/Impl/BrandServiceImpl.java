package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Converter.BrandConverter;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.BrandEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.BrandOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.BrandRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
@Component
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    BrandConverter brandConverter;
    @Override
    public List<BrandOutput> getlistbrand() {
        List<BrandOutput> brandOutputList= new LinkedList<>();
        List<BrandEntity> brandEntityList = brandRepository.findAll();
        for (BrandEntity item: brandEntityList) {
            brandOutputList.add(brandConverter.toBrandEntity(item));
        }
        return brandOutputList;
    }
}
