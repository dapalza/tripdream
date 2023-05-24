package tripdream.com.login;

import org.springframework.data.jpa.repository.JpaRepository;
import tripdream.com.entity.Member;

public interface LoginRepository extends JpaRepository<Member, String> {

    Member findByEmail(String email);
}
