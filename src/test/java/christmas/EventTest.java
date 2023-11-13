package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.Event;

public class EventTest {
    Event event = new Event();

    @DisplayName("1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가한다.")
    @Test
    void 크리스마스_디데이_할인_금액_계산_테스트() {
        // given
        final int FIRST = 1;
        final int CHRISTMAS_DAY = 25;
        final int MIN_DISCOUNT = 1_000;
        final int MAX_DISCOUNT = 3_400;
        int case1 = FIRST;
        int case2 = CHRISTMAS_DAY;
        // when
        int result1 = event.calculateChristmasDiscountAmount(case1);
        int result2 = event.calculateChristmasDiscountAmount(case2);
        // then
        assertThat(result1).isEqualTo(MIN_DISCOUNT);
        assertThat(result2).isEqualTo(MAX_DISCOUNT);
    }
}
