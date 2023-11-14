package com.example.SubProject.Config.auth;

import com.example.SubProject.Entity.MemberEntity;
import com.example.SubProject.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login);
// login 요청이 오면 자동으로 UserDetailsService 타입으로
// ioc 되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    // 매개변수인 username은 변경될시 시큐리티에서 usernameParmeter로 변경

    private final MemberRepository memberRepository;

    @Autowired
    public PrincipalDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // UserDetatils 리턴 =>Authentication(UserDetatils 리턴)
    // 시큐리티 session( Authentication(UserDetatils 리턴) <= UserDetatils 리턴) <=
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByUserId(username);
        if (member != null) {
            return new PrincipalDetails(member);
        }
        return null;
    }
}
