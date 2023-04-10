package ss.dapalza.util;

import java.util.HashSet;
import java.util.UUID;

public class TokenGenerator {
    private static final HashSet<String> accessTokenHash = new HashSet<>();         //final을 붙어셔 모든 TokenGenerator에서 공유
    private static final HashSet<String> refreshTokenHash = new HashSet<>();        // 중복 방지를 위해서
    
    public static synchronized String generateAccessToken(){          //오직 하나의 스레드만 사용가능
        String token;
        do{
            UUID uuid = UUID.randomUUID();
            token = uuid.toString();
        }while (!accessTokenHash.add(token));

        return token;
    }
    public static synchronized String generateRefreshToken(){          //오직 하나의 스레드만 사용가능
        String token;
        do{
            UUID uuid = UUID.randomUUID();
            token = uuid.toString();
        }while (!refreshTokenHash.add(token));

        return token;
    }
}
