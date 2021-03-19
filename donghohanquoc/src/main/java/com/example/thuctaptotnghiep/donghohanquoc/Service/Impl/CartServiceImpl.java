package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Common.PageConstant;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.ProductEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.SizeEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.Cart;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.ProductAtributeRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.ProductRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.SizeRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CartService;
import com.example.thuctaptotnghiep.donghohanquoc.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Component
public class CartServiceImpl implements CartService {
    private static final String REDIRECT_GIO_HANG = "redirect:/gio-hang";
    private static final String AMOUNT = "amount";
    private static final String TOTAL = "total";
    private static final String SESSION_CART = "carts";
    @Autowired
    ProductAtributeRepository productAtributeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Override
    public String pageCart(Model model, HttpSession session) {
        List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
        if (ObjectUtils.isEmpty(carts)) {
            carts = new ArrayList<>();
        }
        int total = carts == null ? 0 : carts.size();
        session.setAttribute(SESSION_CART, carts);
        model.addAttribute(TOTAL, total);
        model.addAttribute(AMOUNT, Utils.currencyMoney((int) Utils.amount(carts)));
        return PageConstant.PAGE_CART;
    }

    @Override
    public String deleteToCart(Model model, HttpSession session, int id) {
        try {
            List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
            int total;

            if (!ObjectUtils.isEmpty(carts)) {
                carts.forEach(cart -> {
                    if (cart.getId() == id) {
                        carts.remove(cart);
                    }
                });
            }
            total = carts == null ? 0 : carts.size();

            session.setAttribute(SESSION_CART, carts);
            model.addAttribute(TOTAL, carts.size());
            model.addAttribute(AMOUNT, Utils.currencyMoney((int) Utils.amount(carts)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT_GIO_HANG;
    }

    @Override
    public String updateToCart(Model model, HttpSession session, int id, int flag) {
        try {
            List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
            int index = 0;
            int count;
            if (!ObjectUtils.isEmpty(carts)) {
                for (Cart cart : carts) {
                    if (cart.getId() == id) {
                        if (flag == 0) {
                            count = cart.getCount() - 1 == 0 ? 1 : cart.getCount() - 1;
                            cart.setCount(count);
                        } else {
                            count = cart.getCount() + 1 == 5 ? 5 : cart.getCount() + 1;
                            cart.setCount(count);
                        }
                        carts.set(index, cart);
                        break;
                    }
                    index++;
                }
            }

            int total = carts == null ? 0 : carts.size();
            session.setAttribute(SESSION_CART, carts);
            model.addAttribute(TOTAL, carts.size());
            model.addAttribute(AMOUNT, Utils.currencyMoney((int) Utils.amount(carts)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT_GIO_HANG;
    }

    @Override
    public String addToCart(Model model, HttpSession session, Integer id, Integer sizeId) {
        try {
            Cart cart = new Cart();
            List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
            boolean flag = false;
            int cartId = 1;
            int index = 0;
            int total;

            if (ObjectUtils.isEmpty(carts)) {
                carts = new ArrayList<>();
            } else {
                for (Cart item : carts) {
                    if (item.getProductId() == id && item.getSizeId() == sizeId) {
                        cart = item;
                        flag = true;
                        index++;
                        break;
                    }
                }
                cartId = carts.get(carts.size() - 1).getId() + 1;
            }
            if (flag) {
                cart.setCount(cart.getCount() + 1);
                carts.set(index - 1, cart);
            } else {
                ProductEntity product = productRepository.findById(id).get();

                SizeEntity size = sizeRepository.findById(sizeId).get();

                cart.setId(cartId);
                cart.setCount(1);
                cart.setProductId(id);
                cart.setSizeId(sizeId);
                cart.setProductDetail(productAtributeRepository.findByProductentityAndSizeentity(product, size));

                carts.add(cart);
            }
            total = carts == null ? 0 : carts.size();

            session.setAttribute(SESSION_CART, carts);
            model.addAttribute(TOTAL, total);
            model.addAttribute(AMOUNT, Utils.currencyMoney((int) Utils.amount(carts)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT_GIO_HANG;
    }


}
