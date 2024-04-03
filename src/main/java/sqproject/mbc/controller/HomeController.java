package sqproject.mbc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sqproject.mbc.security.HomeService;
import sqproject.mbc.vo.MemberVo;

import java.util.HashMap;
import java.util.Map;

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

    //가입완료
    @PostMapping("/signup")
    public String signup(@ModelAttribute MemberVo memberVo, RedirectAttributes redirectAttributes, Model model){
        log.info("signupSave = {}", memberVo.getMId());
        // 검증 오류 결과를 보관
        Map<String, String> errors = new HashMap<>();

        // 검증 로직
        if (!StringUtils.hasText(memberVo.getMId())) {
            errors.put("mId", "아이디는 필수입니다.");
        }
        if (!StringUtils.hasText(memberVo.getMPw())) {
            errors.put("mPw", "비밀번호는 필수입니다.");
        }
        if (!StringUtils.hasText(memberVo.getMName())) {
            errors.put("mName", "이름은 필수입니다.");
        }
        if (!StringUtils.hasText(memberVo.getMBirth())) {
            errors.put("mBirth", "생년월일은 필수입니다.");
        }
        if (!StringUtils.hasText(memberVo.getMNum())) {
            errors.put("mNum", "전화번호는 필수입니다.");
        }

        // 검증에 실패하면 다시 입력 폼으로
        if (!errors.isEmpty()) {
            log.info("errors={}", errors);
            model.addAttribute("errors", errors);
            return "member/signup";
        }

        // 성공로직
        homeservice.save(memberVo);

        return "redirect:/index";
    }
}