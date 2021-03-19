package com.example.thuctaptotnghiep.donghohanquoc.Service;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.CategoryInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.CategoryUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.CategoriesOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriesService{
    ResponseData<Boolean> createCategorybyAdmin(CategoryInput categoryInput);
    List<CategoriesOutput> getListCategories();
    ResponseData<Boolean> deleteCategoryByAdmin(Integer categoryID);
    ResponseData<Boolean> updateCategoryByAdmin(CategoryUpdateInput categoryUpdateInput);
}
