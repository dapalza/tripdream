package tripdream.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tripdream.common.entity.MemberToken;

public interface TokenRepository extends JpaRepository<MemberToken, String>{

}
