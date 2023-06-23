package tripdream.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripdream.common.entity.Token;

@Repository
public interface MemberTokenRepository extends JpaRepository<Token, String> {
}
