package com.example.thuctaptotnghiep.donghohanquoc.Service;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.ReceiverInFor;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public interface PayService {
    String pagePay(HttpSession session, Model model);
    String pay(HttpSession session, ReceiverInFor input);
}
