package com.slshop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.slshop.common.entity.Modal;

@Controller
public class MainController {

    @GetMapping
    public String viewHome() {
        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage(@ModelAttribute("modal") ModelMap modal,Model model) {
    	Modal modalval = (Modal) modal.get("modal");
    	model.addAttribute("modal",modalval);
        return "login";
    }
//    
//    @GetMapping("/cart")
//    public String cartItem() {
//    	return "cart";
//    }
    
   
}
