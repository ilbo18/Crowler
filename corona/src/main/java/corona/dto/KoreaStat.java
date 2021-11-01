package corona.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class KoreaStat {
    private String country; // 도시명
    private int diffFromPrevDay; // 전일대비확진환자증감
    private int total; // 확진환자수
    private int death; // 사망자수
    private int holding; // 격리중
    private int released; // 격리해제
    private int incidence; //발병률
    private int inspection; // 일일 검사환자 수

}
