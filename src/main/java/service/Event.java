package service;

public class Event {
    private static final int MIN_DISCOUNT = 1_000;
    private static final int ADDITIONAL_DISCOUNT = 100;
    private static final int EFFECTIVE_DATE = 1;
    private static final int EXPIRATION_DATE = 25;

    public int calculateChristmasDiscountAmount(int reservation) {
        int discountAmount = MIN_DISCOUNT;
        if (reservation <= EXPIRATION_DATE) {
            for (int date = EFFECTIVE_DATE; date <= reservation; date++) {
                discountAmount += ADDITIONAL_DISCOUNT;
            }
        }
        return discountAmount;
    }

}
