package service;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Event {
    private static final int MIN_DISCOUNT = 1_000;
    private static final int ADDITIONAL_DISCOUNT = 100;
    private static final int EFFECTIVE_DATE = 1;
    private static final int EXPIRATION_DATE = 25;
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private static final int WEEK_DISCOUNT = 2_023;
    private static final int NO_DISCOUNT = 0;

    public int calculateChristmasDiscountAmount(int reservation) {
        int discountAmount = MIN_DISCOUNT;
        if (reservation <= EXPIRATION_DATE) {
            for (int date = EFFECTIVE_DATE; date <= reservation; date++) {
                discountAmount += ADDITIONAL_DISCOUNT;
            }
        }
        return discountAmount;
    }

    public int calculateWeekdayDiscountAmount(int day, int dessertCount) {
        LocalDate reservation = LocalDate.of(EVENT_YEAR, EVENT_MONTH, day);
        DayOfWeek dayOfWeek = reservation.getDayOfWeek();
        if (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY) {
            return dessertCount * WEEK_DISCOUNT;
        }
        return NO_DISCOUNT;
    }

}
