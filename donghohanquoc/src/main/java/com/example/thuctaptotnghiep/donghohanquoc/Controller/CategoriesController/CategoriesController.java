package com.example.thuctaptotnghiep.donghohanquoc.Controller.CategoriesController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.CategoryInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.CategoryUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.BrandOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.CategoriesOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Service.BrandService;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
@Controller
public class CategoriesController {
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    BrandService brandService;
    @PostMapping
    ResponseData<Boolean> createCategoriesByAdmin(@RequestBody CategoryInput categoryInput)
    {
        return categoriesService.createCategorybyAdmin(categoryInput);
    }
    @GetMapping
    ResponseData<List<CategoriesOutput>> getListCategoriesByAdmin()
    {
       /* return categoriesService.getListCategories();*/
        return null;
    }
    @DeleteMapping
    ResponseData<Boolean>  deleteCategoryByAdmin(@RequestBody Integer categoryID)
    {
        return categoriesService.deleteCategoryByAdmin(categoryID);
    }
    @PutMapping
    ResponseData<Boolean> updateCategoryByAdmin(@RequestBody CategoryUpdateInput categoryUpdateInput)
    {
        return  categoriesService.updateCategoryByAdmin(categoryUpdateInput);
    }

}
