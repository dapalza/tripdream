package tripdream.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripdream.common.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
    Token findByRefreshToken(String refreshToken);
}
