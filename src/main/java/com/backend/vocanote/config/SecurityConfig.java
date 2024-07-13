package com.backend.vocanote.config;

import com.backend.vocanote.entity.UserRole;
import com.backend.vocanote.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        // 접근 권한 설정
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/oauth-login/admin").hasRole(UserRole.ADMIN.name()) // "/oauth-login/admin" 경로에 대한 접근 권한 설정 (ADMIN 역할 필요)
                        .requestMatchers("/oauth-login/info").authenticated() // "/oauth-login/info" 경로에 대한 접근 권한 설정 (인증된 사용자 필요)
                        .anyRequest().permitAll()  // 다른 모든 요청은 접근 권한을 허용함
                );

        // 폼 로그인 방식 설정
        http
                .formLogin((auth) -> auth
                        .loginPage("/oauth-login/login") // 로그인 페이지 설정
                        .loginProcessingUrl("/oauth-login/loginProc") // 로그인 처리 URL 설정
                        .usernameParameter("loginId")  // 사용자명 파라미터 설정
                        .passwordParameter("password") // 비밀번호 파라미터 설정
                        .defaultSuccessUrl("/oauth-login") // 로그인 성공 시 기본 URL 설정
                        .failureUrl("/oauth-login") // 로그인 실패 시 URL 설정
                        .permitAll()); // 로그인 페이지는 모든 사용자에게 허용

        // OAuth 2.0 로그인 방식 설정
        http
                .oauth2Login((auth) -> auth.loginPage("/oauth-login/login") // 로그인 페이지 설정
                        .defaultSuccessUrl("/oauth-login") // OAuth 2.0 로그인 성공 시 기본 URL 설정
                        .failureUrl("/oauth-login/login") // OAuth 2.0 로그인 실패 시 URL 설정
                        .permitAll()); // 로그인 페이지는 모든 사용자에게 허용

        http
                .logout((auth) -> auth   // 로그아웃 설정
                        .logoutUrl("/oauth-login/logout"));  // 로그아웃 URL 설정

        http
                .csrf((auth) -> auth.disable()); // CSRF 보호 비활성화

        // HttpSecurity 객체 반환
        return http.build();
    }

    // BCryptPasswordEncoder 빈 설정
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }

}