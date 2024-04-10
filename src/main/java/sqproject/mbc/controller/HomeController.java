package sqproject.mbc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sqproject.mbc.service.CoffeeService;
import sqproject.mbc.service.HomeService;
import sqproject.mbc.vo.CoffeeVo;
import sqproject.mbc.vo.MemberVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final HomeService homeservice;
    private final CoffeeService coffeservice;

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
    public String home(@AuthenticationPrincipal User user, Model model){
        log.info("anth name = {}", user.getUsername());
        List<CoffeeVo> coffeelist = coffeservice.coffeelist();
        log.info("coffeemenulist = {}", coffeelist);

        model.addAttribute("list", coffeelist);
        return "index";
    }

    //회원가입
    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("memberVo", new MemberVo());//th:field 쓰려면 Vo의 빈 객체를 넘겨줘야 함.

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