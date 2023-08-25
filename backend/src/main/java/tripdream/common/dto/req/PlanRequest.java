package tripdream.common.dto.req;

import lombok.Builder;
import lombok.Getter;
import tripdream.common.entity.Chart;
import tripdream.common.entity.CountryCode;

import java.time.LocalDate;

@Getter
@Builder
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
    private CountryCode countryCode;

    // 도시 코드
    private String cityCode;

    // 인원
    private int peopleCount;

    // 만료 여부
    private boolean endYn;

    // 리스트 형태 (LIST/CARD)
    private Chart chart;
}
