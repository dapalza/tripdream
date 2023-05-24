package tripdream.login;

import org.springframework.data.jpa.repository.JpaRepository;
import tripdream.entity.Member;

public interface LoginRepository extends JpaRepository<Member, String> {

    Member findByEmail(String email);
}
