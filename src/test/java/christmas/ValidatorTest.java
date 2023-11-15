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
}
