package sqproject.mbc.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sqproject.mbc.service.CoffeeService;
import sqproject.mbc.vo.CoffeeVo;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CoffeeController {

    private final CoffeeService coffeeservice;

    @GetMapping("/order")
    public String order(
            @AuthenticationPrincipal User user,
            Model model
    ){
        String username = user.getUsername();
        List<CoffeeVo> orderlist = coffeeservice.doorderlist(username);
        model.addAttribute("list", orderlist);
        return "view/order";
    }

    @PostMapping("/ordersuccess")
    public String ordersuccess(@RequestBody CoffeeVo coffeevo) {
        // 받은 데이터 처리
        String orderId = coffeevo.getOrderid();
        String orderno = coffeevo.getOrderno();


        List<CoffeeVo> orderlist = coffeeservice.doorderlist(orderId);
        coffeeservice.doordernoinput(orderlist, orderno);


        return "view/paySucccess";
    }


    //    장바구니에 물건 담은 후 장바구니로 이동
    @GetMapping("/cart")
    public String cartadd(
            @AuthenticationPrincipal User user,
            @ModelAttribute CoffeeVo coffeevo,
            Model model,
            @RequestParam("confirmation") boolean conf
    ) {
        String username = user.getUsername();
        coffeevo.setOrderid(username);
        coffeeservice.docartadd(coffeevo);
        if(!conf) {
            return "redirect:index";
        }
        List<CoffeeVo> orderlist = coffeeservice.doorderlist(username);
        model.addAttribute("list", orderlist);
        return "view/order";
    }

}