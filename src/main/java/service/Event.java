package service;

import domain.Badge;
import domain.Benefit;
import domain.Menu;
import domain.Reservation;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Event {
    private static final int MIN_DISCOUNT = 1_000;
    private static final int ADDITIONAL_DISCOUNT = 100;
    private static final int START_DAY = 1;
    private static final int END_DAY = 25;
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private static final int WEEK_DISCOUNT = 2_023;
    private static final int COUNT_ZERO = 0;
    private static final int SPECIAL_DISCOUNT = 1_000;
    private static final int NO_DISCOUNT = 0;
    private static final int CHRISTMAS_DAY = 25;
    private static final int NO_ORDER = 0;
    private static final int MIN_TOTAL_PRICE_FOR_GIFT = 120_000;
    private static final int MIN_TOTAL_BENEFIT_FOR_SANTA_BADGE = 20_000;
    private static final int MIN_TOTAL_BENEFIT_FOR_TREE_BADGE = 10_000;
    private static final int MIN_TOTAL_BENEFIT_FOR_STAR_BADGE = 5_000;
    private static final int NO_BENEFIT = 0;
    private static final int CHAMPAGNE_PRICE = 25_000;
    private static final int WEEKDAY = 0;
    private static final int WEEKEND = 1;

    public Benefit initializeBenefit(Reservation reservation) {
        int christmasDiscount = calculateChristmasDiscount(reservation.getDay());
        int weekdayOrWeekend = checkWeekdayOrWeekend(reservation.getDay());
        int weekdayDiscountOrWeekendDiscount = calculateWeekDiscount(reservation.getDay(), reservation.getOrder());
        List<Integer> weekDiscount = List.of(weekdayOrWeekend, weekdayDiscountOrWeekendDiscount);
        int specialDiscount = calculateSpecialDiscount(reservation.getDay());
        boolean champagne = offerGiftByTotalPrice(reservation.getOrder());
        return new Benefit(christmasDiscount, weekDiscount, specialDiscount, champagne);
    }

    public int calculateChristmasDiscount(int day) {
        if (day <= END_DAY) {
            return (MIN_DISCOUNT + (day - START_DAY) * ADDITIONAL_DISCOUNT);
        }
        return NO_DISCOUNT;
    }

    public int checkWeekdayOrWeekend(int day) {
        LocalDate reservation = LocalDate.of(EVENT_YEAR, EVENT_MONTH, day);
        DayOfWeek dayOfWeek = reservation.getDayOfWeek();
        if (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY) {
            return WEEKDAY;
        }
        return WEEKEND;
    }

    public int calculateWeekDiscount(int day, Map<Menu, Integer> order) {
        LocalDate date = LocalDate.of(EVENT_YEAR, EVENT_MONTH, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY) {
            return countDessert(order) * WEEK_DISCOUNT;
        }
        return countMain(order) * WEEK_DISCOUNT;
    }

    public int countDessert(Map<Menu, Integer> order) {
        int dessertCount = COUNT_ZERO;
        dessertCount += order.getOrDefault(Menu.초코케이크, COUNT_ZERO);
        dessertCount += order.getOrDefault(Menu.아이스크림, COUNT_ZERO);
        return dessertCount;
    }

    public int countMain(Map<Menu, Integer> order) {
        int mainCount = COUNT_ZERO;
        mainCount += order.getOrDefault(Menu.티본스테이크, COUNT_ZERO);
        mainCount += order.getOrDefault(Menu.바비큐립, COUNT_ZERO);
        mainCount += order.getOrDefault(Menu.해산물파스타, COUNT_ZERO);
        mainCount += order.getOrDefault(Menu.크리스마스파스타, COUNT_ZERO);
        return mainCount;
    }

    public int calculateSpecialDiscount(int day) {
        LocalDate reservation = LocalDate.of(EVENT_YEAR, EVENT_MONTH, day);
        DayOfWeek dayOfWeek = reservation.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY || reservation.getDayOfMonth() == CHRISTMAS_DAY) {
            return SPECIAL_DISCOUNT;
        }
        return NO_DISCOUNT;
    }

    public boolean offerGiftByTotalPrice(Map<Menu, Integer> order) {
        int totalPriceBeforeDiscount = calculateTotalPriceBeforeDiscount(order);
        return totalPriceBeforeDiscount >= MIN_TOTAL_PRICE_FOR_GIFT;
    }

    public int calculateTotalPriceBeforeDiscount(Map<Menu, Integer> order) {
        int totalPriceBeforeDiscount = NO_ORDER;
        for (Menu menu : order.keySet()) {
            totalPriceBeforeDiscount += menu.getPrice() * order.get(menu);
        }
        return totalPriceBeforeDiscount;
    }

    public Badge offerBadgeByTotalBenefit(int totalDiscount, boolean isChampagne) {
        int totalBenefit = calculateTotalBenefit(totalDiscount, isChampagne);
        if (totalBenefit >= MIN_TOTAL_BENEFIT_FOR_SANTA_BADGE) {
            return new Badge("산타");
        }
        if (totalBenefit >= MIN_TOTAL_BENEFIT_FOR_TREE_BADGE) {
            return new Badge("트리");
        }
        if (totalBenefit >= MIN_TOTAL_BENEFIT_FOR_STAR_BADGE) {
            return new Badge("별");
        }
        return new Badge("없음");
    }

    public int calculateTotalBenefit(int totalDiscount, boolean champagne) {
        int totalBenefit = NO_BENEFIT;
        if (champagne == true) {
            totalBenefit += totalDiscount + CHAMPAGNE_PRICE;
        }
        if (champagne == false) {
            totalBenefit += totalDiscount;
        }
        return totalBenefit;
    }
}