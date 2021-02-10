package com.example.thuctaptotnghiep.donghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "size")
@Data
public class SizeEntity {
    private static final long serialVersionUID = -297553281792804396L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @OneToMany(mappedBy = "sizeentity")
    private List<ProductAtributeEntity> productAtributeEntityList = new ArrayList<>();
}
