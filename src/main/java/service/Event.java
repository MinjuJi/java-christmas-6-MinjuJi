package service;

import domain.Menu;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class Event {
    private static final int MIN_DISCOUNT = 1_000;
    private static final int ADDITIONAL_DISCOUNT = 100;
    private static final int EFFECTIVE_DATE = 1;
    private static final int EXPIRATION_DATE = 25;
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private static final int WEEK_DISCOUNT = 2_023;
    private static final int COUNT_ZERO = 0;
    private static final int SPECIAL_DISCOUNT = 1_000;
    private static final int NO_DISCOUNT = 0;
    private static final int CHRISTMAS_DAY = 25;
    private static final int NO_ORDER = 0;

    public int calculateChristmasDiscountAmount(int reservation) {
        int discountAmount = MIN_DISCOUNT;
        if (reservation <= EXPIRATION_DATE) {
            for (int date = EFFECTIVE_DATE; date <= reservation; date++) {
                discountAmount += ADDITIONAL_DISCOUNT;
            }
        }
        return discountAmount;
    }

    public int calculateWeekDiscountAmount(int day, Map<Menu, Integer> order) {
        LocalDate reservation = LocalDate.of(EVENT_YEAR, EVENT_MONTH, day);
        DayOfWeek dayOfWeek = reservation.getDayOfWeek();
        if (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY) {
            return countDessert(order) * WEEK_DISCOUNT;
        }
        return countMain(order) * WEEK_DISCOUNT;
    }

    public int countDessert(Map<Menu, Integer> order) {
        int dessertCount = COUNT_ZERO;
        dessertCount += order.getOrDefault("초코케이크", COUNT_ZERO);
        dessertCount += order.getOrDefault("아이스크림", COUNT_ZERO);
        return dessertCount;
    }

    public int countMain(Map<Menu, Integer> order) {
        int dessertCount = COUNT_ZERO;
        dessertCount += order.getOrDefault("티본스테이크", COUNT_ZERO);
        dessertCount += order.getOrDefault("바비큐립", COUNT_ZERO);
        dessertCount += order.getOrDefault("해산물파스타", COUNT_ZERO);
        dessertCount += order.getOrDefault("크리스마스파스타", COUNT_ZERO);
        return dessertCount;
    }

    public int calculateSpecialDiscount(int day) {
        LocalDate reservation = LocalDate.of(EVENT_YEAR, EVENT_MONTH, day);
        DayOfWeek dayOfWeek = reservation.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY || reservation.getDayOfMonth() == CHRISTMAS_DAY) {
            return SPECIAL_DISCOUNT;
        }
        return NO_DISCOUNT;
    }

    public int calculateTotalPriceBeforeDiscount(Map<Menu, Integer> order) {
        int totalPrice = NO_ORDER;
        for (Menu menu : order.keySet()) {
            totalPrice += menu.getPrice() * order.get(menu);
        }
        return totalPrice;
    }

}