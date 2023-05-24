package tripdream.com.register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripdream.com.entity.Member;

@Repository
public interface RegisterRepository extends JpaRepository<Member, String> {


}
