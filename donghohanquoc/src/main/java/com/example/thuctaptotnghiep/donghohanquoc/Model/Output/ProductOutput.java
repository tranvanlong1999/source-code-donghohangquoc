package com.example.thuctaptotnghiep.donghohanquoc.Model.Output;
import lombok.Data;

import java.util.Date;

@Data
public class ProductOutput {
    private Integer id;
    private String productname;
    private String description;
    private Integer brandid;
    private Integer categoryid;
    private String PriceStr;
    private int price;
    private Date createAt;
    private Integer status;
    private String Image;
    private String brandname;
    private String categoryname;
    private Integer productDetailId;

}
