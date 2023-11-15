package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import validator.Validator;

public class ValidatorTest {
    @DisplayName("입력값이 숫자가 아니면 예외를 발생시킨다.")
    @Test
    void 입력값_숫자_테스트() {
        // given
        String case1 = "1";
        String case2 = "일";

        // when
        Throwable result1 = catchThrowable(() -> {
            Validator.validateInputIsNumeric(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            Validator.validateInputIsNumeric(case2);
        });

        // then
        assertThat(result1).doesNotThrowAnyException();
        assertThat(result2).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문할 날짜가 1 이상 31 이하의 범위가 아니면 예외를 발생시킨다.")
    @Test
    void 방문_날짜_범위_테스트() {
        // given
        int case1 = 1;
        int case2 = 31;
        int case3 = 0;
        int case4 = 32;

        // when
        Throwable result1 = catchThrowable(() -> {
            Validator.validateDataInRange(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            Validator.validateDataInRange(case2);
        });
        Throwable result3 = catchThrowable(() -> {
            Validator.validateDataInRange(case3);
        });
        Throwable result4 = catchThrowable(() -> {
            Validator.validateDataInRange(case4);
        });

        // then
        assertThat(result1).doesNotThrowAnyException();
        assertThat(result2).doesNotThrowAnyException();
        assertThat(result3).isInstanceOf(IllegalArgumentException.class);
        assertThat(result4).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예약한 주문이 메뉴판에 없는 메뉴라면 예외를 발생시킨다.")
    @Test
    void 주문_메뉴_존재_테스트() {
        // given
        String[] case1 = new String[]{"초코케이크"};
        String[] case2 = new String[]{"딸기케이크"};

        // when
        Throwable result1 = catchThrowable(() -> {
            Validator.validateExistingMenu(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            Validator.validateExistingMenu(case2);
        });

        // then
        assertThat(result1).doesNotThrowAnyException();
        assertThat(result2).isInstanceOf(IllegalArgumentException.class);
    }
}
