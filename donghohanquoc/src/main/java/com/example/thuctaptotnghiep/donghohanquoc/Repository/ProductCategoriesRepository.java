package com.example.thuctaptotnghiep.donghohanquoc.Repository;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductCategoriesEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoriesRepository extends JpaRepository<ProductCategoriesEntity,Integer> {
    List<ProductCategoriesEntity> findByProductEntity(ProductEntity productEntity);
}
