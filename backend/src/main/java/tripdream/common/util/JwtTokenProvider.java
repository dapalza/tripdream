package tripdream.common.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
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
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    // 비밀 키
    private final Key key;

    // 현재 시간
    LocalDateTime localNow = LocalDateTime.now();

    // Access token 만료 시간 : 1분
    final int accessTokenExpireLong = 1;

    // Refresh token 만료 시간 : 60분
    final int refreshTokenExpireLong = 60;

    final Date accessTokenExpireAt = Timestamp.valueOf(localNow.plusMinutes(accessTokenExpireLong));
    final Date refreshTokenExpireAt = Timestamp.valueOf(localNow.plusMinutes(refreshTokenExpireLong));

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 유저 정보로 AccessToken, RefreshToken 생성
    public LoginToken generateToken(Authentication authentication) {
        log.info("call generate token");
        String authorities = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpireAt)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpireAt)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        log.info("now = {}", localNow);
        log.info("accessToken info ={}", accessToken);
        log.info("accessTokenExpireLong info ={}", accessTokenExpireLong);
        log.info("accessTokenExpireAt info ={}", accessTokenExpireAt);
        log.info("refreshToken info ={}", refreshToken);
        log.info("refreshTokenExpireAt info ={}", refreshTokenExpireAt);

        return LoginToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireAt(localNow.plusMinutes(accessTokenExpireLong))
                .refreshTokenExpireAt(localNow.plusMinutes(refreshTokenExpireLong))
                .build();
    }

    // JWT 토큰 복호화해서 토큰 내 정보 읽기
    public Authentication getaAuthentication(String accessToken) {
        log.info("call get authentication");
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

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
    public boolean validateAccessToken(String accessToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty", e);
        }
        return false;
    }

    // 리프레시 토큰 검증
    public String validateRefreshToken(String refreshToken) {
        try {
            Jws<Claims> claims =
                    Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(refreshToken);

            if (!claims.getBody().getExpiration().before(Timestamp.valueOf(LocalDateTime.now()))) {
                // Access Token 생성
                String accessToken = Jwts.builder()

                        .setExpiration(accessTokenExpireAt)
                        .signWith(key, SignatureAlgorithm.HS256)
                        .compact();
                return accessToken;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return null;
        }
    }

    // 클레임 (jwt 정보 단위) 파싱
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
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
