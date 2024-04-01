package sqproject.mbc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sqproject.mbc.security.HomeService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @Autowired
    HomeService homeservice;

    //test
    @GetMapping("status")
    public String testst(){
        return "OK";
    }


    //로그인페이지
    @GetMapping("/login")
    public String login(){

        return "/view/login";
    }

    //인덱스
    @GetMapping("/index")
    public String home(){
        return "index";
    }
    
    //로그인 성공시
    @PostMapping("/logged")
    public String logged(@RequestParam Model model){

        boolean isValidMember =  homeservice.logged(model);
        if(isValidMember)
            return "/view/login";
        return "index";
    }

    //회원가입
    @GetMapping("/signup")
    public String signup(){
        return "view/signup";
    }

}