package com.example.SubProject.Config.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    //구글로 부터 받은 userRequest 데이터 후처리 되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Registration 구글 , 클라이언트 정보들
        log.info("userRequest={}" ,userRequest.getClientRegistration());
        //엑세스 토큰 값
        log.info("getAccessToken={}" ,userRequest.getAccessToken().getTokenValue());
        log.info("getAdditionalParameters={}" ,userRequest.getAdditionalParameters());
        // 받아온 정보 {sub=109131749677441791147, name=김호준, given_name=호준, family_name=김,
        // picture=https://lh3.googleusercontent.com/a/ACg8ocKemQ-XBTVhTh8ENE0WcrazSGVmqwQ2bU3X4D_BYbPK=s96-c, email=hojune961207@gmail.com, email_verified=true, locale=ko}
        System.out.println("getAttributes: "+ super.loadUser(userRequest).getAttributes());
        return super.loadUser(userRequest);
    }
}
