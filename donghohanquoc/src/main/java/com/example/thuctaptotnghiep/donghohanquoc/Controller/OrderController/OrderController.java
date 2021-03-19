package com.example.thuctaptotnghiep.donghohanquoc.Controller.OrderController;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ProductOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {
    @GetMapping("/order")
    public String getListOrder(Model model) {
        return "cart-page";
    }
}
