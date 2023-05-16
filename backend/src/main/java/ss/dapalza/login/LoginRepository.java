package ss.dapalza.login;

import org.springframework.data.jpa.repository.JpaRepository;
import ss.dapalza.entity.Member;

public interface LoginRepository extends JpaRepository<Member, String> {

    Member findByEmail(String email);
}
