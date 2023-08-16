package tripdream.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tripdream.common.dto.req.PlanRequest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plan extends CommonEntity {
    @Id
    @GeneratedValue
    @Column(name = "PLAN_ID")
    private Long id;

    // 여행 시작일
    @NotNull
    private LocalDate startDate;

    // 여행 종료일
    @NotNull
    private LocalDate endDate;

    // 여행 기간
    private int duration;

    // 여행 제목
    private String title;

    // 국가 코드
    @NotNull
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;

    // 도시 코드
    @NotNull
    private String cityCode;

    // 인원
    private int peopleCount;

    // 만료 여부 (Y/N)
    private boolean endYn;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @PrePersist
    public void insertDefaultValue() {
        StringBuilder sb = new StringBuilder("제목");
        duration = getEndDate().compareTo(startDate);
        title = title == null ? sb.append(id).toString() : title;
        peopleCount = peopleCount == 0 ? 1 : peopleCount;
    }

    public Plan(PlanRequest planRequest) {
        this.startDate = planRequest.getStartDate();
        this.endDate = planRequest.getEndDate();
        this.duration = planRequest.getDuration();
        this.title = planRequest.getTitle();
        this.countryCode = planRequest.getCountryCode();
        this.cityCode = planRequest.getCityCode();
        this.peopleCount = planRequest.getPeopleCount();
        this.endYn = planRequest.isEndYn();
    }

    public void changePlanId(Long planId) {
        this.id = planId;
    }

}
