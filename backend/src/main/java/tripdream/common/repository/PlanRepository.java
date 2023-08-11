package tripdream.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tripdream.common.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
