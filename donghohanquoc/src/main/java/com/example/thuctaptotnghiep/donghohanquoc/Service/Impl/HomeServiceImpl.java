package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.SizeEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.SizeRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class HomeServiceImpl implements HomeService {
    @Autowired
    SizeRepository sizeRepository;
    @Override
    public List<SizeEntity> getListSize() {
        return sizeRepository.findAll();
    }
}
