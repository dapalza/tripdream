package tripdream.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripdream.common.entity.MemberToken;

@Repository
public interface MemberTokenRepository extends JpaRepository<MemberToken, String> {
}
