package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import domain.Menu;
import java.util.HashMap;
import java.util.Map;
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

    @DisplayName("주문 메뉴의 총 개수가 1 이상 20 이하의 범위가 아니면 예외를 발생시킨다.")
    @Test
    void 주문_메뉴_총_개수_테스트() {
        // given
        Map<Menu, Integer> case1 = new HashMap<>();
        case1.put(Menu.크리스마스파스타, 20);
        Map<Menu, Integer> case2 = new HashMap<>();
        case2.put(Menu.크리스마스파스타, 1);
        Map<Menu, Integer> case3 = new HashMap<>();
        case3.put(Menu.크리스마스파스타, 21);
        Map<Menu, Integer> case4 = new HashMap<>();
        case4.put(Menu.크리스마스파스타, 0);

        // when
        Throwable result1 = catchThrowable(() -> {
            Validator.validateMenuCountInRange(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            Validator.validateMenuCountInRange(case2);
        });
        Throwable result3 = catchThrowable(() -> {
            Validator.validateMenuCountInRange(case3);
        });
        Throwable result4 = catchThrowable(() -> {
            Validator.validateMenuCountInRange(case4);
        });

        // then
        assertThat(result1).doesNotThrowAnyException();
        assertThat(result2).doesNotThrowAnyException();
        assertThat(result3).isInstanceOf(IllegalArgumentException.class);
        assertThat(result4).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효하지 않은 메뉴 형식으로 입력하면 예외를 발생시킨다.")
    @Test
    void 메뉴_형식_테스트() {
        // given
        String[] case1 = new String[]{"해산물파스타-2", "레드와인-1", "초코케이크-1"};
        String[] case2 = new String[]{"해산물파스타/2", "레드와인/1", "초코케이크/1"};

        // when
        Throwable result1 = catchThrowable(() -> {
            Validator.validateOrderFormat(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            Validator.validateOrderFormat(case2);
        });

        // then
        assertThat(result1).doesNotThrowAnyException();
        assertThat(result2).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 메뉴를 입력하면 예외를 발생시킨다.")
    @Test
    void 중복_메뉴_테스트() {
        // given
        String[] case1 = new String[]{"해산물파스타-2", "레드와인-1", "초코케이크-1"};
        String[] case2 = new String[]{"레드와인-2", "레드와인-1", "초코케이크-1"};

        // when
        Throwable result1 = catchThrowable(() -> {
            Validator.validateDuplicatedMenu(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            Validator.validateDuplicatedMenu(case2);
        });

        // then
        assertThat(result1).doesNotThrowAnyException();
        assertThat(result2).isInstanceOf(IllegalArgumentException.class);
    }
}
