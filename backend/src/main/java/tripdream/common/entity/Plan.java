package tripdream.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    @Id
    @GeneratedValue
    @Column(name = "PLAN_ID")
    private long id;

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
    private String countryCode;

    // 도시 코드
    @NotNull
    private String cityCode;

    // 인원
    private int peopleCount;

    // 만료 여부 (Y/N)
    private String endYn;

    // 리스트 형태 (LIST/CARD)
    private String chart;

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
        endYn = endYn == null ? "N" : endYn;
        chart = chart == null ? "LIST" : "CARD";
    }

}
