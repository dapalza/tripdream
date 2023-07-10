package tripdream.common.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tripdream.common.vo.LoginToken;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    // 비밀 키
    private final Key key;

    // Access token 만료 시간 : 1분
    final int accessTokenExpireLong = 1;

    // Refresh token 만료 시간 : 60분
    final int refreshTokenExpireLong = 60;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 유저 정보로 AccessToken, RefreshToken 생성
    public LoginToken generateToken(Authentication authentication) {
        log.info("call generate token");

        // Access Token 생성
        String accessToken = makeAccessToken(authentication);

        // Refresh Token 생성
        String refreshToken = makeRefreshToken();

        log.info("now = {}", LocalDateTime.now());
        log.info("accessToken info ={}", accessToken);
        log.info("accessTokenExpireLong info ={}", accessTokenExpireLong);
        log.info("accessTokenExpireAt info ={}", Timestamp.valueOf(LocalDateTime.now().plusMinutes(accessTokenExpireLong)));
        log.info("refreshToken info ={}", refreshToken);
        log.info("refreshTokenExpireAt info ={}", Timestamp.valueOf(LocalDateTime.now().plusMinutes(refreshTokenExpireLong)));

        return LoginToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireAt(LocalDateTime.now().plusMinutes(accessTokenExpireLong))
                .refreshTokenExpireAt(LocalDateTime.now().plusMinutes(refreshTokenExpireLong))
                .build();
    }

    private String makeRefreshToken() {
        return Jwts.builder()
                // 토큰 생성 시간
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                // 토큰 만료 시간
                .setExpiration(Timestamp.valueOf(LocalDateTime.now().plusMinutes(refreshTokenExpireLong)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private String makeAccessToken(Authentication authentication) {
        String authorities = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                // 토큰 생성 시간
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                // 토큰 만료 시간
                .setExpiration(Timestamp.valueOf(LocalDateTime.now().plusMinutes(accessTokenExpireLong)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT 토큰 복호화해서 토큰 내 정보 읽기
    public Authentication getAuthentication(String token) {
        log.info("call get authentication");
        // 토큰 복호화
        Claims claims = parseClaims(token);

        if(claims.get("auth") == null) {
            log.error("auth is null");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);

        log.info("end get authentication");
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);

    }

    // 액세스 토큰 정보 검증
    public boolean isValidAccessToken(String accessToken) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken);
        if(claimsJws != null)  {
            log.info("valid access token");
            return true;
        }
        log.info("invalid access token");
        return false;
    }

    // 리프레시 토큰 검증


    private String regenerateAccessToken(Authentication authentication) {
        log.info("call regenerate token");

        // Access Token 생성
        return makeAccessToken(authentication);
    }

    // 클레임 (jwt 정보 단위) 파싱
    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }


    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String memberId = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();


        return null;
    }

    public boolean supports(Class<?> authentication) {
        return false;
    }
}
