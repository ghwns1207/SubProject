package com.example.SubProject.Config.auth;


// 시큐리티가 /login 주소요청이 오면 낚아채서 로그인을 진행시킨다
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다.(시큐리티 콘테스트홀더)
// 오브젝트 => Authentication 타입객체
// Authentication 안에 user 정보가 있어야 됨.
// user 오브젝트의 타입 => userDetails 타입 객체

import com.example.SubProject.Entity.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 시큐리티 session => Authentication => UserDetails타입(PrincipalDetails)
public class PrincipalDetails implements UserDetails {

    private MemberEntity member; //콤포지션

    public PrincipalDetails(MemberEntity member) {
        this.member = member;
    }

    //해당 User의 권환을 리턴하는 곳!!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collection;
    }

    @Override // 패스워드 리턴
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override // 계정 만료
    public boolean isAccountNonExpired() {
        if (member.getUseyn() != 1) {
            System.out.println("여기다");
            return false;
        }
        return true;
    }

    @Override // 계정이 잠겼는지 확인
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 비밀번호 유효기간이 지났는지 확인
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 계정이 활성화 되어 있는지 (휴면 계정 확인)
    public boolean isEnabled() {

        // 우리 사이트에서 1년 동안 로그인을 안학 휴면 계정으로 되어 있다면
        // 현재시간 - 마지막 로그인 시간 => 1년이 초과하면 return false;
        return true;
    }
}
