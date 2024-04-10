package sqproject.mbc.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String cartadd(@ModelAttribute CoffeeVo coffeevo, Model model, RequestParam conf){
        if(conf.equals("false")) {
            coffeeservice.docartadd(coffeevo);
            return "redirect:/menu";
        }
        List<CoffeeVo> orderlist = coffeeservice.doorderlist();
        model.addAttribute("list", orderlist);
        return "menu/coffeeorder";
    }

    //    장바구니에 물건 담은 후 메뉴로 이동
    @GetMapping("/cartback")
    public String cartaddback(@ModelAttribute CoffeeVo coffeevo){

        coffeeservice.docartadd(coffeevo);

    }
}
}
