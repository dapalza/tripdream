package tripdream.plan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tripdream.common.entity.Plan;
import tripdream.common.repository.PlanRepositoryTest;

import java.time.LocalDate;

@SpringBootTest
class PlanServiceTest {

    @Autowired
    PlanRepositoryTest planRepository;

    LocalDate localDate;

    @Test
    public void storePlan() {
        Plan plan = Plan.builder()
                .chart("LIST")
                .countryCode("KOR")
                .startDate(localDate.now())
                .endDate(localDate.now())
                .endYn("N")
                .title("나의 여행기")
                .cityCode("16881")
                .duration(10)
                .peopleCount(3)
                .build();
        Plan savedPlan = planRepository.save(plan);

        Assertions.assertThat(plan.getId()).isEqualTo(savedPlan.getId());
    }

    @Test
    public void modifyPlan() {
        Plan plan = Plan.builder()
                .chart("LIST")
                .countryCode("KOR")
                .startDate(localDate.now())
                .endDate(localDate.now())
                .endYn("N")
                .title("나의 여행기")
                .cityCode("16881")
                .duration(10)
                .peopleCount(3)
                .build();

        Plan savedPlan = planRepository.save(plan);

        Plan newPlan = Plan.builder()
                .chart("BOARD")
                .countryCode("ENG")
                .startDate(localDate.now().plusDays(2))
                .endDate(localDate.now().plusDays(9))
                .endYn("Y")
                .title("나의 여행기 수정본")
                .cityCode("16889")
                .duration(7)
                .peopleCount(6)
                .build();

        newPlan.changePlanId(savedPlan.getId());
        planRepository.save(newPlan);

        Plan savedNewPlan = planRepository.findById(savedPlan.getId()).orElseThrow();

        Assertions.assertThat(savedNewPlan.getChart()).isEqualTo(newPlan.getChart());
    }

}