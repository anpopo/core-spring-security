package io.security.corespringsecurity.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authenticationProvider;

    /**
     * 데이터 베이스와 연동하여 인증 및 권한 설정을 하기 위해 CustomAuthenticationProvider 객체를 설정해 줌.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/users").permitAll()
                .antMatchers("/mypage").hasRole("USER")
                .antMatchers("/messages").hasRole("MANAGER")
                .antMatchers("/config").hasRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin();
    }

    // WebIgnore 설정 = js / css / images 등 필터를 적용할 필요가 없는 리소스 설정
    // ignoring() 은 보안필터를 거치지 않음
    // permitAll() 은 보안 필터를 거쳐 패턴 매칭
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
