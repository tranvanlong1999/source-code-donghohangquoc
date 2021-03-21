package com.example.thuctaptotnghiep.donghohanquoc.Service;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.SizeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeService {
    List<SizeEntity> getListSize();
}
