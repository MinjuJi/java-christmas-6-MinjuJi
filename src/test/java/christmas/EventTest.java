package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Badge;
import domain.Menu;
import java.util.HashMap;
import java.util.Map;
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
        final int EXPIRED_DAY = 26;
        final int MIN_DISCOUNT = 1_000;
        final int MAX_DISCOUNT = 3_400;
        final int NO_DISCOUNT = 0;
        int case1 = FIRST;
        int case2 = CHRISTMAS_DAY;
        int case3 = EXPIRED_DAY;

        // when
        int result1 = event.calculateChristmasDiscount(case1);
        int result2 = event.calculateChristmasDiscount(case2);
        int result3 = event.calculateChristmasDiscount(case3);

        // then
        assertThat(result1).isEqualTo(MIN_DISCOUNT);
        assertThat(result2).isEqualTo(MAX_DISCOUNT);
        assertThat(result3).isEqualTo(NO_DISCOUNT);
    }

    @DisplayName("평일에는 디저트 메뉴, 주말에는 메인 메뉴를 1개당 2,023원 할인한다.")
    @Test
    void 평일_주말_할인_금액_계산_테스트() {
        // given
        final int THURSDAY = 7;
        final int Friday = 30;
        final int WEEKDAY_DISCOUNT = 6_069;
        final int WEEKEND_DISCOUNT = 4_046;
        int case1 = THURSDAY;
        int case2 = Friday;
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.valueOf("초코케이크"), 3);
        order.put(Menu.valueOf("크리스마스파스타"), 2);

        // when
        int result1 = event.calculateWeekDiscount(case1, order);
        int result2 = event.calculateWeekDiscount(case2, order);

        // then
        assertThat(result1).isEqualTo(WEEKDAY_DISCOUNT);
        assertThat(result2).isEqualTo(WEEKEND_DISCOUNT);
    }

    @DisplayName("일요일 또는 크리스마스에 1,000원 할인한다.")
    @Test
    void 특별_할인_금액_계산_테스트() {
        // given
        final int CHRISTMAS_DAY = 25;
        final int SUNDAY = 31;
        final int SATURDAY = 30;
        final int SPECIAL_DISCOUNT = 1_000;
        final int NO_DISCOUNT = 0;
        int case1 = CHRISTMAS_DAY;
        int case2 = SUNDAY;
        int case3 = SATURDAY;

        // when
        int result1 = event.calculateSpecialDiscount(case1);
        int result2 = event.calculateSpecialDiscount(case2);
        int result3 = event.calculateSpecialDiscount(case3);

        // then
        assertThat(result1).isEqualTo(SPECIAL_DISCOUNT);
        assertThat(result2).isEqualTo(SPECIAL_DISCOUNT);
        assertThat(result3).isEqualTo(NO_DISCOUNT);
    }

    @DisplayName("할인 전 총주문 금액이 12만 원 이상일 때, true를 반환한다.")
    @Test
    void 샴페인_증정_기능_테스트() {
        // given
        final boolean NO_GIFT = false;
        final boolean GIFT = true;
        final int ORDER_QUANTITY_TWO = 2;
        final int ORDER_QUANTITY_ONE = 1;
        final Map<Menu, Integer> case1 = new HashMap<>();
        case1.put(Menu.valueOf("양송이수프"), ORDER_QUANTITY_TWO);
        case1.put(Menu.valueOf("티본스테이크"), ORDER_QUANTITY_ONE);
        final Map<Menu, Integer> case2 = new HashMap<>();
        case2.put(Menu.valueOf("티본스테이크"), ORDER_QUANTITY_TWO);
        case2.put(Menu.valueOf("아이스크림"), ORDER_QUANTITY_TWO);

        // when
        boolean result1 = event.offerGiftByTotalPrice(case1);
        boolean result2 = event.offerGiftByTotalPrice(case2);

        // then
        assertThat(result1).isEqualTo(NO_GIFT);
        assertThat(result2).isEqualTo(GIFT);
    }

    @DisplayName("총혜택 금액 구간 별 이벤트 배지를 부여한다.")
    @Test
    void 이벤트_배지_부여_테스트() {
        // given
        final int LESS_STAR = 3_000;
        final int MORE_STAR = 5_000;
        final int MORE_TREE = 10_000;
        final int MORE_SANTA = 20_000;
        final boolean HAS_NOT_CHAMPAGNE = false;
        final String NOTHING = "없음";
        final String STAR = "별";
        final String TREE = "트리";
        final String SANTA = "산타";
        final int case1 = LESS_STAR;
        final int case2 = MORE_STAR;
        final int case3 = MORE_TREE;
        final int case4 = MORE_SANTA;

        // when
        Badge result1 = event.offerBadgeByTotalBenefit(case1, HAS_NOT_CHAMPAGNE);
        Badge result2 = event.offerBadgeByTotalBenefit(case2, HAS_NOT_CHAMPAGNE);
        Badge result3 = event.offerBadgeByTotalBenefit(case3, HAS_NOT_CHAMPAGNE);
        Badge result4 = event.offerBadgeByTotalBenefit(case4, HAS_NOT_CHAMPAGNE);

        // then
        assertThat(result1.getBadge()).isEqualTo(NOTHING);
        assertThat(result2.getBadge()).isEqualTo(STAR);
        assertThat(result3.getBadge()).isEqualTo(TREE);
        assertThat(result4.getBadge()).isEqualTo(SANTA);
    }
}
