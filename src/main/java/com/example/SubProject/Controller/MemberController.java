package com.example.SubProject.Controller;

import com.example.SubProject.Entity.MemberEntity;
import com.example.SubProject.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService= memberService;
    }

    @PostMapping("/mlogin")
    public ResponseEntity<MemberEntity> login(@RequestParam String userId, @RequestParam String userPW) {
        try {
            System.out.println("받기 까지 성공");
            // 로그인 처리
           MemberEntity member = memberService.userLogin(userId).orElseThrow(() ->
                   new IllegalArgumentException("user not found"));
            if(member == null || !userPW.equals(member.getPassword())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }else{
                return ResponseEntity.ok(member);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.toString();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
