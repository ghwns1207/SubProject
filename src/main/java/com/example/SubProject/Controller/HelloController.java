package com.example.SubProject.Controller;


import com.example.SubProject.Entity.MemberEntity;
import com.example.SubProject.Service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HelloController {

    private final MemberService memberService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public HelloController(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.memberService = memberService;
    }

    @GetMapping({"", "/"})
    public String MainPage(Model model) {
        model.addAttribute("serviceName", "안녕하세요");
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }


    @GetMapping("/login")
    public String LoginPage() {
        return "LoginPage";
    }

    @GetMapping("/Singup")
    public String SinUpPage() {
        return "Singup";
    }

    @PostMapping("/msingup")
    public String msingup(MemberEntity memberEntity, Model model) {
        log.info("memberEntity={}", memberEntity); // 로그 찍는 법
        String encodingPw = passwordEncoder.encode(memberEntity.getPassword());
        memberEntity.setPassword(encodingPw);
        if (memberService.userSingUp(memberEntity)) {
            return "redirect:/login";

        } else {
            model.addAttribute("회원가입 실패");
            return "회원가입 실패";
        }
    }

}
