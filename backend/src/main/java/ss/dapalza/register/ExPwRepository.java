package ss.dapalza.register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ss.dapalza.entity.Expw;

@Repository
public interface ExPwRepository extends JpaRepository<Expw, String> {
}
