package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Constants.ResCode;
import com.example.thuctaptotnghiep.donghohanquoc.Converter.CategoriesConverter;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.CategoriesEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.CategoryInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.CategoryUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.CategoriesOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.CategoriesRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    CategoriesConverter categoriesConverter;
    @Override
    public ResponseData<Boolean> createCategorybyAdmin(CategoryInput categoryInput) {
        ResponseData<Boolean> responseData= new ResponseData<>();
        try
        {
            CategoriesEntity categoriesEntity;
            categoriesEntity= categoriesConverter.toCategoriesInput(categoryInput);
            categoriesRepository.save(categoriesEntity);
            responseData.setCode(ResCode.SUCCESS.getCode());
            responseData.setMessage(ResCode.SUCCESS.getMessage());
            responseData.setData(true);
        } catch (Exception e) {
            responseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
            responseData.setMessage(ResCode.UNKNOWN_ERROR.getMessage());
            responseData.setData(false);
        }
        return responseData;
    }

    @Override
    public List<CategoriesOutput> getListCategories() {
        List<CategoriesOutput> categoriesOutputList = new LinkedList<>();
        // lay list
        try {
            List<CategoriesEntity> categoriesEntityList=categoriesRepository.findAll();
            for (CategoriesEntity categoriesEntity: categoriesEntityList) {
                categoriesOutputList.add(new CategoriesConverter().toCategoriesEntity(categoriesEntity));
            }
        } catch (Exception e) {

        }
        return categoriesOutputList;
    }

    @Override
    public ResponseData<Boolean> deleteCategoryByAdmin(Integer categoryID) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        try {
            // tim kiem category ID  trong danh sach category
            CategoriesEntity categoriesEntity= categoriesRepository.findById(categoryID).get();
            if(ObjectUtils.isEmpty(categoriesEntity))
            {
                throw new Exception("danh muc khong ton tai ");
            }
            if(!ObjectUtils.isEmpty(categoriesEntity))
            {
                categoriesRepository.delete(categoriesEntity);
                responseData.setCode(ResCode.SUCCESS.getCode());
                responseData.setMessage(ResCode.SUCCESS.getMessage());
                responseData.setData(true);
            }
        } catch (Exception e) {
                responseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
                responseData.setMessage(ResCode.UNKNOWN_ERROR.getMessage());
                responseData.setData(false);
        }
        return responseData;
    }

    @Override
    public ResponseData<Boolean> updateCategoryByAdmin(CategoryUpdateInput categoryUpdateInput) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        try {
            if(!ObjectUtils.isEmpty(categoryUpdateInput))
            {
                CategoriesEntity categoriesEntity = categoriesRepository.findById(categoryUpdateInput.getId()).get();
                if(ObjectUtils.isEmpty(categoriesEntity))
                    throw new Exception("khong tim thay danh muc can sua");
                categoriesRepository.save(new CategoriesConverter().toCategoriUpdateInput(categoryUpdateInput));
                responseData.setCode(ResCode.SUCCESS.getCode());
                responseData.setMessage(ResCode.SUCCESS.getMessage());
                responseData.setData(true);
            }
        } catch (Exception e) {
                responseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
                responseData.setMessage(ResCode.UNKNOWN_ERROR.getMessage());
                responseData.setData(false);
        }
        return responseData;
    }
}
