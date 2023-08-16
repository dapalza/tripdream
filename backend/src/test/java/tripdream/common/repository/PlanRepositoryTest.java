package tripdream.common.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripdream.common.entity.CountryCode;
import tripdream.common.entity.Plan;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Repository
public interface PlanRepositoryTest extends JpaRepository<Plan, Long> {
}