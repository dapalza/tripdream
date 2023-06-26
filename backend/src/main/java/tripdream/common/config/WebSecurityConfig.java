package tripdream.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tripdream.common.filter.JwtAuthenticationFilter;
import tripdream.common.util.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

    private final JwtTokenProvider jwtTokenProvider;

    // JWT를 사용하기 위해서는 기본적으로 password encoder가 필요함.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // REST API이기에 basic auth, csrf 보안 사용해제
                .httpBasic().disable()
                .cors().disable()
                // 서버에 인증정보 저장하지 않음. jwt 사용
                .csrf().disable()
                // JWT를 사용하기 때문에 세션을 사용하지 않음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 회원가입 요청은 로그인을 요구하지 않음
                .antMatchers("/api/register").permitAll()
                // 로그인 요청은 로그인을 요구하지 않음
                .antMatchers("/api/login").permitAll()
                // 이미지 업로드 구현용으로 일단 열기
                .antMatchers("/api/image/upload").permitAll()
                .anyRequest().authenticated()
                .and()
                // 커스텀 필터를 UsernamePasswordAuthenticationFilter 전에 실행
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
