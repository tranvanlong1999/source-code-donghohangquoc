package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Constants.Constants;
import com.example.thuctaptotnghiep.donghohanquoc.Constants.ResCode;
import com.example.thuctaptotnghiep.donghohanquoc.Converter.ProductConverter;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductAtributeEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductAtributeInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.*;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    @Override
    public ResponseData<List<ProductOutput>> getListProduct() {
        ResponseData<List<ProductOutput>> listResponseData= new ResponseData<>();
        try {
            List<ProductEntity> productEntityList= productRepository.findAll();
            List<ProductOutput> productOutputList= new LinkedList<>();
            for (ProductEntity productEntity: productEntityList) {
                productOutputList.add(new ProductConverter().toProductEntity(productEntity));
            }
            listResponseData.setCode(ResCode.SUCCESS.getCode());
            listResponseData.setMessage(ResCode.SUCCESS.getMessage());
            listResponseData.setData(productOutputList);
        } catch (Exception e) {
            listResponseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
            listResponseData.setMessage(ResCode.UNKNOWN_ERROR.getMessage());
        }
        return listResponseData;
    }

    @Override
    public ResponseData<Integer> createProductByAdmin(ProductInput productInput) {
        ResponseData<Integer> responseData= new ResponseData<>();
        try {
            List<ProductAtributeEntity>productAtributeEntityList= new ArrayList<>();
            List<ProductAtributeInput> productAtributeInputList= productInput.getProductAtributeInputList();
            ProductEntity productEntity= productConverter.toProductInput(productInput);
            // case brand is null
            if(ObjectUtils.isEmpty(productEntity.getBrandentity()))
                productEntity.setBrandentity(brandRepository.findById(Constants.BRAND_DEFAULT).get());
            // save product to db
            productEntity=productRepository.save(productEntity);
            //set data to list product atribtute
            if(productAtributeInputList.size()>0)
            for (ProductAtributeInput productAtributeInput: productAtributeInputList) {
                ProductAtributeEntity productAtributeEntity= new ProductAtributeEntity();
                productAtributeEntity.setProductentity(productEntity);
                productAtributeEntity.setSizeentity(sizeRepository.findById(productAtributeInput.getSizeid()).get());
                productAtributeEntity.setColorentity(colorRepository.findById(productAtributeInput.getColorid()).get());
                productAtributeEntityList.add(productAtributeEntity);
            }


            // save product atribute vao db
            productAtributeRepository.saveAll(productAtributeEntityList);

            responseData.setCode(ResCode.SUCCESS.getCode());
            responseData.setMessage(ResCode.SUCCESS.getMessage());
            responseData.setData(productEntity.getId());
        } catch (Exception e) {
            responseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
            responseData.setMessage(ResCode.UNKNOWN_ERROR.getMessage());
        }
        return responseData;
    }
}
