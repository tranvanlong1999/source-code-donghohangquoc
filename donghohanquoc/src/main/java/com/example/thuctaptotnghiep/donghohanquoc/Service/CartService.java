package com.example.thuctaptotnghiep.donghohanquoc.Service;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ProductAtributeInput;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public interface CartService {
     String addToCart(Model model, HttpSession session, ProductAtributeInput input);
     String pageCart(Model model,HttpSession session);
     String deleteToCart(Model model, HttpSession session, int id);
     String updateToCart(Model model, HttpSession session, int id, int flag);
     String deleteAllCart(Model model, HttpSession session);
}
