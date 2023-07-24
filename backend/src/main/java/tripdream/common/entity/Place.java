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
public class Place extends CommonEntity{
    @Id
    @GeneratedValue
    @Column(name = "PLACE_ID")
    private Long id;

    // 장소명
    @NotNull
    private String title;

    // 도로명 주소
    private String roadAddress;

    // 방문 날짜
    @NotNull
    private LocalDate visitDate;

    // 카테고리 (아이콘 배정 등 디자인 용도)
    private String category;

    // 방문 순서
    private int priority;

    // 장소 메모
    private String memo;

    @ManyToOne
    @JoinColumn(name = "PLAN_ID")
    private Plan plan;

    @OneToOne
    @JoinColumn(name = "BUDGET_ID")
    private Budget budget;
}
