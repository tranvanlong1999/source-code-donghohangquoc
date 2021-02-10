package com.example.thuctaptotnghiep.donghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="product")
@Data
public class ProductEntity {
    private static final long serialVersionUID = -297553281792804396L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Product_Name")
    private String productname;
    @Column(name="Image")
    private String image;
    @Column(name = "Price")
    private int price;
    @Column(name="Description")
    private String description;
    @Column(name="Status")
    private Integer status;
    @ManyToOne
    @JoinColumn(name="Brand_ID")
    private BrandEntity brandentity;
    @Column(name="Quantity_Sold")
    private Integer quantitysold;
    @Column(name="Quantity_Remaining")
    private Integer quantityremaining;
    @Column(name="Created_At")
    private Date createdat;
    @Column(name="Created_By")
    private String createdby;
    @Column(name = "Updated_At")
    private Date updatedat;
    @Column(name = "Updated_By")
    private String updatedby;
    /*// map voi ben productatributeentity
    @OneToMany(mappedBy = "productentity")
    private List<ProductAtributeEntity> productAtributeEntityList= new ArrayList<>();*/
}
