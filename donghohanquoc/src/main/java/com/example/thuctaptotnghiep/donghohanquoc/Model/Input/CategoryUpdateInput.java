package com.example.thuctaptotnghiep.donghohanquoc.Model.Input;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryUpdateInput {
    private Integer id;
    private String name;
    private String description;
    private Integer status;
    private String updatedby;
}
