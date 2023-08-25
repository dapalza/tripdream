package tripdream.common.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripdream.common.entity.Plan;

@SpringBootTest
@Repository
public
interface PlanRepositoryTest extends JpaRepository<Plan, Long> {
}