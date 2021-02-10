package com.example.thuctaptotnghiep.donghohanquoc.Model.Input;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.BrandEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductInput {
    private String productname;
    private String image;
    private Integer price;
    private String description;
    private BrandEntity brandentity;
    private Integer quantitysold;
    private Integer quantityremaining;
    private String createdby;
    private List<ProductAtributeInput> productAtributeInputList;
}
