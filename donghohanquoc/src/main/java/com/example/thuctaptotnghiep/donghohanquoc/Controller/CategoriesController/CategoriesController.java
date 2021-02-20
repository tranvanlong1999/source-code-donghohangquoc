package com.example.thuctaptotnghiep.donghohanquoc.Controller.CategoriesController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.CategoryInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.CategoryUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.CategoriesOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    CategoriesService categoriesService;
    @PostMapping
    ResponseData<Boolean> createCategoriesByAdmin(@RequestBody CategoryInput categoryInput)
    {
        return categoriesService.createCategorybyAdmin(categoryInput);
    }
    @GetMapping
    ResponseData<List<CategoriesOutput>> getListCategoriesByAdmin()
    {
        return categoriesService.getListCategories();
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
