package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Converter.ProductConverter;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.*;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductConverter productConverter;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    ProductAtributeRepository productAtributeRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public Page<ProductEntity> getListProduct(Integer status, Pageable pageable, Model model) {
        Page<ProductEntity> list = productRepository.findlistofstatus(pageable);
        int totalPages = list.getTotalPages();
        long tongbanghi= list.getTotalElements();
        model.addAttribute("size",pageable.getPageSize());
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("tongbanghi",tongbanghi);
        model.addAttribute("currentPage",list.getNumber());
        if(totalPages>0)
        {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages-1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return list;
    }
    @Override
    public ProductEntity getProductEntity(Integer productid) {
        return productRepository.findById(productid).get();
    }
}
