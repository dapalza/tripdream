package tripdream.login;

import org.springframework.data.jpa.repository.JpaRepository;
import tripdream.entity.MemberToken;

public interface TokenRepository extends JpaRepository<MemberToken, String>{

}
