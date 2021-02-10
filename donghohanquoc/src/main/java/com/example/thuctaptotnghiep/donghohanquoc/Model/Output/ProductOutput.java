package com.example.thuctaptotnghiep.donghohanquoc.Model.Output;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.BrandEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ProductOutput {
    private Integer id;
    private String productname;
    private String image;
    private Integer price;
    private String description;
    private int status;
    private BrandEntity brandentity;
    private int quantitysold;
    private int quantityremaining;
    private Date createdat;
    private String createdby;
    private Date updatedat;
    private String updatedby;
    private String path;
}
