package com.example.SubProject.Controller;

import com.example.SubProject.Entity.MemberEntity;
import com.example.SubProject.Service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //로그 찍기
@RestController
public class MemberController {

    private final MemberService memberService;
    private  BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberController(MemberService memberService, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.memberService = memberService;
    }

//    @PostMapping("/mlogin")
//    public ResponseEntity<MemberEntity> login(@RequestParam String userId, @RequestParam String userPW) {
//        try {
//            System.out.println("받기 까지 성공");
//            // 로그인 처리
//            MemberEntity member = memberService.userLogin(userId).orElseThrow(() ->
//                    new IllegalArgumentException("user not found"));
//            if (member == null || !) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//            } else {
//                return ResponseEntity.ok(member);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.info("e={}", e.toString());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

//    @PostMapping("/msingup")
//    public ResponseEntity<String> msingup(MemberEntity memberEntity) {
//        log.info("memberEntity={}", memberEntity); // 로그 찍는 법
//        String encodingPw = passwordEncoder.encode(memberEntity.getPassword());
//        memberEntity.setPassword(encodingPw);
//        if (memberService.userSingUp(memberEntity)) {
//            return ResponseEntity.ok("회원가입 성공");
//
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("회원가입 실패!!");
//        }
//    }

}

