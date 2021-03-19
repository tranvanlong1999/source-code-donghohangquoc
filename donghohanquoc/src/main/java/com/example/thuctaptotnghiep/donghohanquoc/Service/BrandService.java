package com.example.thuctaptotnghiep.donghohanquoc.Service;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.BrandOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    List<BrandOutput> getlistbrand();
}
