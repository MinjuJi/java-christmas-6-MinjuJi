package domain;

public class Benefit {

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

    public int calculateTotalDiscount() {
        return christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public int getWeekdayDiscount() {
        return weekdayDiscount;
    }

    public int getWeekendDiscount() {
        return weekendDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public boolean isChampagne() {
        return champagne;
    }

}
