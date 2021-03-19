package com.example.thuctaptotnghiep.donghohanquoc.Controller.CartController;
import com.example.thuctaptotnghiep.donghohanquoc.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("gio-hang")
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping
    public String pageCart(Model model, HttpSession session) {
        return cartService.pageCart(model, session);
    }

    @GetMapping("add")
    public String addToCart(Model model, HttpSession session, @RequestParam("id") Integer id,@RequestParam("size") int size )
    {
        return cartService.addToCart(model, session, id, size);
    }
    @GetMapping("update")
    public String updateToCart(Model model, HttpSession session, @RequestParam("id") int id,
                               @RequestParam("flag") int flag) {
        return cartService.updateToCart(model, session, id, flag);
    }

    @GetMapping("delete")
    public String deleteToCart(Model model, HttpSession session, @RequestParam("id") int id) {
        return cartService.deleteToCart(model, session, id);
    }

}
