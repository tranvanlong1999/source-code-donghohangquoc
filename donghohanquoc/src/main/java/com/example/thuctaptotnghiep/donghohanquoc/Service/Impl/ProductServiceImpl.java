package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Constants.Constants;
import com.example.thuctaptotnghiep.donghohanquoc.Constants.ResCode;
import com.example.thuctaptotnghiep.donghohanquoc.Converter.ProductConverter;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductAtributeEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductCategoriesEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductAtributeInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductCategoriesInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.*;
import com.example.thuctaptotnghiep.donghohanquoc.Service.ProductService;
import com.example.thuctaptotnghiep.donghohanquoc.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.rmi.CORBA.Util;
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
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    ProductCategoriesRepository productCategoriesRepository;
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
            List<ProductCategoriesEntity> productCategoriesEntityList= new ArrayList<>();
            List<ProductAtributeInput> productAtributeInputList= productInput.getProductAtributeInputList();
            List<ProductCategoriesInput> productCategoriesInputList= productInput.getProductCategoriesInputList();

            ProductEntity productEntity= productConverter.toProductInput(productInput);
            // case brand is null
            if(ObjectUtils.isEmpty(productEntity.getBrandentity()))
                productEntity.setBrandentity(brandRepository.findById(Constants.BRAND_DEFAULT).get());
            // save product to db
            productEntity=productRepository.save(productEntity);
            // set data in product
            productEntity.setProductname(productEntity.getProductname()+"MS" +productEntity.getId());
            productEntity.setPath(Utils.formatStringtoUrl(productEntity.getProductname()));
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
            // set data to list product categories
            for (ProductCategoriesInput productCategoriesInput: productCategoriesInputList) {
                ProductCategoriesEntity productCategoriesEntity= new ProductCategoriesEntity();
                productCategoriesEntity.setProductEntity(productEntity);
                productCategoriesEntity.setCategoriesEntity(categoriesRepository.findById(productCategoriesInput.getCategoriesid()).get());
                productCategoriesEntityList.add(productCategoriesEntity);
            }
            // save productcategories vao db
            productCategoriesRepository.saveAll(productCategoriesEntityList);

            responseData.setCode(ResCode.SUCCESS.getCode());
            responseData.setMessage(ResCode.SUCCESS.getMessage());
            responseData.setData(productEntity.getId());
        } catch (Exception e) {
            responseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
            responseData.setMessage(ResCode.UNKNOWN_ERROR.getMessage());
        }
        return responseData;
    }

    @Override
    public ResponseData<Boolean> deleteProductByAdmin(Integer productid) {
        ResponseData<Boolean> responseData= new ResponseData<>();
        try
        {
            // lay ra product can xoa dua vao id
            ProductEntity productEntity= productRepository.findById(productid).get();
            if(ObjectUtils.isEmpty(productEntity))
            {
                throw new Exception("Product not exist");
            }
            // lay list product atributed
            List<ProductAtributeEntity> productAtributeEntityList= productAtributeRepository.findbyProductEntity(productEntity);
            // lay list product categories
            List<ProductCategoriesEntity> productCategoriesEntityList=productCategoriesRepository.findByProductEntity(productEntity);

            //delete list productatributed
            productAtributeRepository.deleteAll(productAtributeRepository.findbyProductEntity(productEntity));
            // delete list productcategorie
            productCategoriesRepository.deleteAll(productCategoriesRepository.findByProductEntity(productEntity));
            // xoa product khoi db
            productRepository.delete(productEntity);
            responseData.setCode(ResCode.SUCCESS.getCode());
            responseData.setMessage(ResCode.SUCCESS.getMessage());
            responseData.setData(true);

        } catch (Exception e) {
            responseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
            responseData.setMessage(ResCode.UNKNOWN_ERROR.getMessage());
            responseData.setData(false);
        }
        return responseData;
    }

    @Override
    public ResponseData<Boolean> updateProductByAdmin(ProductUpdateInput productUpdateInput) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        try {
            if(!ObjectUtils.isEmpty(productUpdateInput))
            {
                //
                List<ProductAtributeEntity> productAtributeEntityList= new ArrayList<>();
                List<ProductAtributeInput> productAtributeInputList= productUpdateInput.getProductAtributeInputList();
                List<ProductAtributeEntity> productAtributeEntityAdds= new ArrayList<>();
                List<ProductCategoriesInput> productCategoriesInputList= productUpdateInput.getProductCategoriesInputList();
                List<ProductCategoriesEntity> productCategoriesEntityAdds= new ArrayList<>();
                List<ProductAtributeEntity> productAtributeEntities;
                List<ProductAtributeEntity> productCategoriesEntityDeletes= new ArrayList<>();
                // lay product theo id cua productinput muon cap nhap
                ProductEntity productEntity= productRepository.findById(productUpdateInput.getId()).get();
                if(!ObjectUtils.isEmpty(productEntity))
                {
                    throw new Exception("product khong ton tai");
                }
                productEntity= productConverter.toProductUpdateInput(productUpdateInput);
                // truong hop brandentity la null thi se gan mac dinh
                if(ObjectUtils.isEmpty(productEntity.getBrandentity()))
                {
                    productEntity.setBrandentity(brandRepository.findById(Constants.BRAND_DEFAULT).get());
                }
                // set data in list product atribute
                for (ProductAtributeInput productAtributeInput: productAtributeInputList) {
                    ProductAtributeEntity productAtributeEntity= new ProductAtributeEntity();
                    // truong hop la update
                    if(productAtributeInput.getId()>0)
                    {
                        productAtributeEntity = productAtributeRepository.findById(productAtributeInput.getId()).get();
                        productAtributeEntityList.add(productAtributeEntity);
                    }
                    // truong hop la create
                    else
                    {
                        productAtributeEntity.setProductentity(productEntity);
                    }
                    //data common
                    productAtributeEntity.setSizeentity(sizeRepository.findById(productAtributeInput.getId()).get());
                    productAtributeEntity.setColorentity(colorRepository.findById(productAtributeInput.getId()).get());
                    productAtributeEntityAdds.add(productAtributeEntity);
                }
                //set data in  list productcategories
                for (ProductCategoriesInput productCategoriesInput:productCategoriesInputList ) {
                    ProductCategoriesEntity productCategoriesEntity= new ProductCategoriesEntity();
                    // case update productcategories
                    if(productCategoriesInput.getId()>0)
                    {
                        productCategoriesEntity= productCategoriesRepository.findById(productCategoriesInput.getId()).get();
                    }
                    else
                    {
                        productCategoriesEntity.setProductEntity(productEntity);
                    }
                    productCategoriesEntityAdds.add(productCategoriesEntity);
                }
                // get list product atribute by product trong bb
                productAtributeEntities= productAtributeRepository.findbyProductEntity(productEntity);
                for (ProductAtributeEntity item: productAtributeEntities) {
                    if(!productAtributeEntityList.contains(item))
                    {
                        productCategoriesEntityDeletes.add(item);
                    }
                }

                // save product in db
                productEntity= productRepository.save(productEntity);
                // save all product atribute in db
                if(!productAtributeEntityAdds.isEmpty())
                {
                    productAtributeRepository.saveAll(productAtributeEntityAdds);
                }
                // save all product categories in db
                if(!productCategoriesEntityAdds.isEmpty())
                {
                    productCategoriesRepository.saveAll(productCategoriesEntityAdds);
                }
                // delete list product atribute cu
                if(!productCategoriesEntityDeletes.isEmpty())
                {
                    productAtributeRepository.deleteAll(productCategoriesEntityDeletes);
                }
                responseData.setCode(ResCode.SUCCESS.getCode());
                responseData.setMessage(ResCode.SUCCESS.getMessage());
                responseData.setData(true);
            }
        } catch (Exception e) {
                responseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
                responseData.setMessage(ResCode.UNKNOWN_ERROR.getMessage());
                responseData.setData(false);
        }
        return responseData;
    }
}
