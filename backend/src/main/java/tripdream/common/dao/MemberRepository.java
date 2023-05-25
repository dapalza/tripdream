package tripdream.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripdream.common.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmail(String email);
}
