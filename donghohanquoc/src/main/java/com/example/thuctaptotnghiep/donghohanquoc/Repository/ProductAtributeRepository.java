package com.example.thuctaptotnghiep.donghohanquoc.Repository;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductAtributeEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductAtributeRepository extends JpaRepository<ProductAtributeEntity, Integer> {
    @Query("SELECT e from ProductAtributeEntity e where e.productentity= :productentity")
    List<ProductAtributeEntity> findbyProductEntity(@Param("productentity") ProductEntity productEntity);
    @Query("select e from ProductAtributeEntity e where e.productentity.id=:productid")
    ProductAtributeEntity findByProductid(Integer productid);
    @Query(value = "SELECT * FROM donghohanquoc.product_atribute where product_id=?1", nativeQuery = true)
    List<ProductAtributeEntity> findByProductId(Integer id);
}
