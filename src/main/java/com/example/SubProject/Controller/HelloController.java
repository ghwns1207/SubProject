package com.example.SubProject.Controller;


import com.example.SubProject.Entity.MemberEntity;
import com.example.SubProject.Service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public HelloController(MemberService memberService, BCryptPasswordEncoder passwordEncoder) {
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
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager() {
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
        if(memberEntity.getRole() == null){
            memberEntity.setRole("ROLE_USER");
        }
        if (memberService.userSingUp(memberEntity)) {
            return "redirect:/login";

        } else {
            model.addAttribute("회원가입 실패");
            return "회원가입 실패";
        }
    }

    // 시큐리티 설정으로 글로벌로 걸면 되지만 특정 호출만 하고 싶을때 사용
    @Secured("ROLE_ADMIN") //하나만 되는듯
    // @EnableGlobalMethodSecurity(securedEnabled = true)시큐어 활성화 시키고 어드민 계정이 아니면 접속 차단
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }


    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") //여러개 사용하고 싶을떄
    //메서드 실행전 권환 확인 여러개 가능
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "데이터 정보";
    }


}
