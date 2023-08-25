package tripdream.common.dto.req;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PlanRequest {
    // 여행 시작일
    private LocalDate startDate;

    // 여행 종료일
    private LocalDate endDate;

    // 여행 기간
    private int duration;

    // 여행 제목
    private String title;

    // 국가 코드
    private String countryCode;

    // 도시 코드
    private String cityCode;

    // 인원
    private int peopleCount;

    // 만료 여부 (Y/N)
    private String endYn;

    // 리스트 형태 (LIST/CARD)
    private String chart;
}
