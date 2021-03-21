package com.example.thuctaptotnghiep.donghohanquoc.Service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public interface CartService {
     String addToCart(Model model, HttpSession session, Integer id, Integer sizeId);
     String pageCart(Model model,HttpSession session);
     String deleteToCart(Model model, HttpSession session, int id);
     String updateToCart(Model model, HttpSession session, int id, int flag);
     String deleteAllCart(Model model, HttpSession session);
}
