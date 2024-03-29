package tripdream.common.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import tripdream.common.util.JwtTokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            // 1. Request Header에서 순수 JWT 토큰만 추출
            String token = resolveAccessToken((HttpServletRequest) request);

            // 2. validateToken으로 토큰 유효성 검사
            if (token != null && jwtTokenProvider.isValidToken(token)) {
                // 유효한 토큰일 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            log.error("exception shows in filter = {}", e.toString());
            request.setAttribute("exception", e);
        } finally {
            chain.doFilter(request, response);
        }
    }


    // Request Header에서 토큰 정보 추출
    private String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // 토큰 맨 앞에 Bearer를 붙임
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            log.info("bearer token front = {}", bearerToken.substring(0, 7));
            log.info("bearer token back = {}", bearerToken.substring(7));
            return bearerToken.substring(7);
        }
        return null;
    }
}
