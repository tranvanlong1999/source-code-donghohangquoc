package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.OrderEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.OrderItemEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ReceiverInFor;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.Cart;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.OrderItemRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.OrderRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Service.PayService;
import com.example.thuctaptotnghiep.donghohanquoc.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class PayServiceImpl implements PayService {
    private static final String AMOUNT = "amount";
    private static final String SESSION_CART = "carts";
    private static final String REDIRECT_GIO_HANG = "redirect:/gio-hang";
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderDetailRepository;
    @SuppressWarnings("unchecked")
    @Override
    public String pagePay(HttpSession session, Model model) {
        String result = "checkout";
        List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
        if(ObjectUtils.isEmpty(carts)) {
            result = REDIRECT_GIO_HANG;
        }
        model.addAttribute("thongtinnguoinhan", new ReceiverInFor());
        model.addAttribute("totalMoney", Utils.currencyMoney((int) Utils.amount(carts)));
        model.addAttribute(AMOUNT, Utils.currencyMoney((int) Utils.amount(carts) + 15000));
        return result;
    }
    @SuppressWarnings("unchecked")
    @Transactional(rollbackOn = Exception.class)
    @Override
    public String pay(HttpSession session, ReceiverInFor input) {
        try {
            List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
            List<OrderItemEntity> orderDetails = new ArrayList<>();
            OrderEntity order = new OrderEntity();
            int amount = 0;

            for (Cart cart : carts) {
                amount += cart.getCount() * cart.getProductDetail().getProductentity().getPrice();
            }

            order.setRecipientaddress(input.getRecipientAddress());
            order.setCreatedate(new Date());
            order.setTotal(amount);
            order.setEmail(input.getRecipientEmail());
            order.setRecipientname(input.getRecipientName());
            order.setRecipientphone(input.getRecipientPhone());
            order.setStatus(1);

            order = orderRepository.save(order);

            for (Cart cart : carts) {
                OrderItemEntity orderDetail = new OrderItemEntity();

                orderDetail.setOrderEntity(order);
                orderDetail.setQuantity(cart.getCount());
                orderDetail.setProductAtributeEntity(cart.getProductDetail());
                orderDetails.add(orderDetail);
            }
            orderDetailRepository.saveAll(orderDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/home";
    }
}
