package com.example.SubProject.Config;

import com.example.SubProject.Config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됩니다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//securedEnabled = true  시큐어 어노테이션 활성화,
// prePostEnabled = true 프리어솔라이즈 어노테이션 활성화
public class SecurityConfig {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean // 해당 메서드의 리턴되는 오브젝트를 ioc 등룍
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //이게 필터
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(CsrfConfigurer :: disable); //CSRF 보호 비활성화
        http.authorizeHttpRequests(authorize ->
                authorize
                        .requestMatchers("/user/**").authenticated()
                        // 유저라는 것으로 들어오면 인증 필요, 로그인 한 사람만
                        .requestMatchers("/manager/**").hasAnyRole("ADMIN","MANAGER") // "ROLE_ADMIN" 이런 형식으로 디비에 저장해야됨
                        // 어드민이나, 매니저 권환이 있는 사람만 접속가능, 로그인을 했지만~ 어드민이나, 매니저 권환이 있어야
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().permitAll()  // 위의 3개의 주소가 아니면 모든 접속 허용

                )
                .formLogin(formLogin ->
                        formLogin.loginPage("/login")
                                .usernameParameter("userid")
                                .loginProcessingUrl("/mlogin") //login 주솨 호출되면 시큐리티가 낚아채서 대신 로그인 진행
                                .defaultSuccessUrl("/")
                ).oauth2Login( oauth2Login ->
                                oauth2Login
                                        .loginPage("/login") //구글 로그인이 완료된 뒤의 후처리 필요.
                                                        // 1 코드 받다(인증 2. 엑세스 토큰(권환이 생김) 3.사용자 프로필 정보 가져옴
                                                        // 4.회원가입 자동으로 처리하거나 or 회원의 정보가 부족하면 추가정보를 받고 회원가입
                                        .userInfoEndpoint(userInfoEndpoint ->
                                                        userInfoEndpoint
                                                                .userService(principalOauth2UserService)
                                                )
                                        //코드x(엑세스토큰+ 사용자프로필 정보
                        );
        return http.build();
    }

}
