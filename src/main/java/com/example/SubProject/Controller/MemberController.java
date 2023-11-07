package com.example.SubProject.Controller;

import Entity.MemberEntity;
import Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController (MemberService memberService){
        this.memberService= memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<MemberEntity> login(@RequestParam String userId, @RequestParam String userPW) {
        try {
            // 로그인 처리
           MemberEntity member = memberService.userLogin(userId);
            if(member == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }else if(userPW.equals(member.getPassword())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }else{
                return ResponseEntity.ok(member);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
