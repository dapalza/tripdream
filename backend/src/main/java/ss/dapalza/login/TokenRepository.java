package ss.dapalza.login;

import org.springframework.data.jpa.repository.JpaRepository;
import ss.dapalza.entity.DPZToken;

public interface TokenRepository extends JpaRepository<DPZToken, String>{

}
