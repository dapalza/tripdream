package ss.dapalza.register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ss.dapalza.entity.Member;

@Repository
public interface RegisterRepository extends JpaRepository<Member, String> {


}
