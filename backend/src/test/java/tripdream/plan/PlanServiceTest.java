package tripdream.plan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tripdream.common.entity.CountryCode;
import tripdream.common.entity.Plan;
import tripdream.common.repository.PlanRepositoryTest;

import java.time.LocalDate;

@SpringBootTest
class PlanServiceTest {

    @Autowired
    PlanRepositoryTest planRepository;

    @Test
    public void storePlan() {
        Plan plan = Plan.builder()
                .countryCode(CountryCode.KOR)
                .startDate(LocalDate.of(2023, 8, 16))
                .endDate(LocalDate.of(2023, 8, 30))
                .endYn(false)
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
                .countryCode(CountryCode.KOR)
                .startDate(LocalDate.of(2023, 8, 16))
                .endDate(LocalDate.of(2023, 8, 30))
                .endYn(false)
                .title("나의 여행기")
                .cityCode("16881")
                .duration(10)
                .peopleCount(3)
                .build();

        Plan savedPlan = planRepository.save(plan);

        Plan newPlan = Plan.builder()
                .countryCode(CountryCode.USA)
                .startDate(LocalDate.of(2023, 8, 16).plusDays(2))
                .endDate(LocalDate.of(2023, 8, 30).plusDays(9))
                .endYn(true)
                .title("나의 여행기 수정본")
                .cityCode("16889")
                .duration(7)
                .peopleCount(6)
                .build();

        newPlan.changePlanId(savedPlan.getId());
        planRepository.save(newPlan);

        Plan savedNewPlan = planRepository.findById(savedPlan.getId()).orElseThrow();

        Assertions.assertThat(savedNewPlan.getTitle()).isEqualTo(newPlan.getTitle());
    }

    @Test
    public void searchPlanAll() {
        Plan plan1 = Plan.builder()
                .countryCode(CountryCode.KOR)
                .startDate(LocalDate.of(2023, 8, 16))
                .endDate(LocalDate.of(2023, 8, 30))
                .endYn(false)
                .title("나의 여행기")
                .cityCode("16881")
                .duration(10)
                .peopleCount(3)
                .build();

        planRepository.save(plan1);

        Plan plan2 = Plan.builder()
                .countryCode(CountryCode.USA)
                .startDate(LocalDate.of(2023, 8, 16).plusDays(2))
                .endDate(LocalDate.of(2023, 8, 30).plusDays(9))
                .endYn(true)
                .title("나의 여행기 수정본")
                .cityCode("16889")
                .duration(7)
                .peopleCount(6)
                .build();

        planRepository.save(plan2);


    }

}