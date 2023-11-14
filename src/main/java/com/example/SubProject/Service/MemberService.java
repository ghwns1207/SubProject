package com.example.SubProject.Service;

import com.example.SubProject.Entity.MemberEntity;
import com.example.SubProject.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Optional<MemberEntity> userLogin(String userId){return memberRepository.userLogin(userId);}

    public boolean userSingUp(MemberEntity memberEntity){return  memberRepository.userSingUpo(memberEntity);}

}
