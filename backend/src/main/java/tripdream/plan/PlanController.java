package tripdream.plan;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tripdream.common.dto.res.PlanResponse;
import tripdream.common.entity.Plan;
import tripdream.common.exception.NoSuchPlanException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {
    private final PlanService planService;

    @PostMapping("/create")
    public ResponseEntity<PlanResponse> createPlan(@RequestBody Plan plan) {
        PlanResponse response = new PlanResponse();



        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanResponse> modifyPlan(@RequestBody Plan plan) {
        if(plan.getId() == null) {
            throw new NoSuchPlanException();
        }

        PlanResponse response = new PlanResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
