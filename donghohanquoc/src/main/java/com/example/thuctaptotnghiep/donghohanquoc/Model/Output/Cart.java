package com.example.thuctaptotnghiep.donghohanquoc.Model.Output;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductAtributeEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Utils.Utils;
import lombok.Data;

@Data
public class Cart{
    private int id;
    private int count;
    private int productId;
    private int sizeId;
    private ProductAtributeEntity productDetail;
    public String getAmount() {
        int money = (int) (this.productDetail.getProductentity().getPrice() * this.count);
        return Utils.currencyMoney(money);
    }
}
