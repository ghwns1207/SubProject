package com.example.SubProject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됩니다.
public class SecurityConfig {

    @Bean // 해당 메서드의 리턴되는 오브젝트를 ioc 등룍
    public PasswordEncoder passwordEncoder() {
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
                        .requestMatchers("/manager/**").hasAnyRole("ADMIN","MANAGER")
                        // 어드민이나, 매니저 권환이 있는 사람만 접속가능, 로그인을 했지만~ 어드민이나, 매니저 권환이 있어야
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().permitAll()  // 위의 3개의 주소가 아니면 모든 접속 허용

                )
                .formLogin(formLogin ->
                        formLogin.loginPage("/login")
                                .usernameParameter("userid")
                                .loginProcessingUrl("/mlogin") //login 주솨 호출되면 시큐리티가 낚아채서 대신 로그인 진행
                                .defaultSuccessUrl("/")
                );
        return http.build();
    }

}
