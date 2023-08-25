package tripdream.plan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tripdream.common.entity.Plan;
import tripdream.common.repository.PlanRepository;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    public Plan storePlan(Plan plan) {
        planRepository.save(plan);
        return plan;
    }
}
