package tripdream.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tripdream.common.entity.Token;

public interface TokenRepository extends JpaRepository<Token, String>{

}
