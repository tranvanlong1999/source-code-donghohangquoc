package com.example.thuctaptotnghiep.donghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_atribute")
@Data
public class ProductAtributeEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Product_ID")
    private ProductEntity productentity;
    @ManyToOne
    @JoinColumn(name="Size_ID")
    private   SizeEntity sizeentity;
    @ManyToOne
    @JoinColumn(name="Color_ID")
    private ColorEntity colorentity ;

}
