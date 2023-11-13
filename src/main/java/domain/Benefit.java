package domain;

public class Benefit {
    private static final int NO_BENEFIT = 0;
    private static final int CHAMPAGNE_PRICE = 25_000;
    private final int christmasDiscount;
    private final int weekdayDiscount;
    private final int weekendDiscount;
    private final int specialDiscount;
    private final boolean champagne;

    public Benefit(int christmasDiscount, int weekdayDiscount, int weekendDiscount, int specialDiscount,
                   boolean champagne) {
        this.christmasDiscount = christmasDiscount;
        this.weekdayDiscount = weekdayDiscount;
        this.weekendDiscount = weekendDiscount;
        this.specialDiscount = specialDiscount;
        this.champagne = champagne;
    }

    public int calculateTotalBenefit() {
        int totalBenefit = NO_BENEFIT;
        if (champagne == true) {
            totalBenefit = christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount + CHAMPAGNE_PRICE;
        }
        if (champagne == false) {
            totalBenefit = christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
        }
        return totalBenefit;
    }
}
