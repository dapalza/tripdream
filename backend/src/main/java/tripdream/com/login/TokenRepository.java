package tripdream.com.login;

import org.springframework.data.jpa.repository.JpaRepository;
import tripdream.com.entity.MemberToken;

public interface TokenRepository extends JpaRepository<MemberToken, String>{

}
