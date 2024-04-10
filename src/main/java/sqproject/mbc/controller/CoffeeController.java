package sqproject.mbc.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import sqproject.mbc.service.CoffeeService;
import sqproject.mbc.vo.CoffeeVo;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CoffeeController {

    private final CoffeeService coffeeservice;

    @GetMapping("/order")
    public String order(){

        return "view/order";
    }


    //    장바구니에 물건 담은 후 장바구니로 이동
    @GetMapping("/cart")
    public String cartadd(@AuthenticationPrincipal User user, @ModelAttribute CoffeeVo coffeevo, Model model, @RequestParam("confirmation") boolean conf){
        String username = user.getUsername();
        model.addAttribute("orderid", username);
        System.out.println(username+"======================username");
        System.out.println(conf+"======================conf");
        coffeeservice.docartadd(coffeevo);
        if(!conf) {
            return "redirect:/menu";
        }
        List<CoffeeVo> orderlist = coffeeservice.doorderlist(username);
        model.addAttribute("list", orderlist);
        return "menu/coffeeorder";
    }

}