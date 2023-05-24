package tripdream.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                // 서버에 인증정보 저장하지 않음. jwt 사용
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/signIn").permitAll()
              //  .antMatchers("/api/signIn").permitAll()
                .anyRequest().permitAll();
//                .formLogin().disable()
//                .headers().frameOptions().disable();
        return http.build();
    }
}
