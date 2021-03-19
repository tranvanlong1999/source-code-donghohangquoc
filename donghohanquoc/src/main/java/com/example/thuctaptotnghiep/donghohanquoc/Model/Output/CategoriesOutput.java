package com.example.thuctaptotnghiep.donghohanquoc.Model.Output;

import lombok.Data;

import java.util.Date;

@Data
public class CategoriesOutput {
    private Integer id;
    private String name;
    private String Description;
    private Integer status;
    private Date createdat;
    private String createdby;
    private  Date updatedat;
    private String updatedby;
    private Integer isformen;
}
