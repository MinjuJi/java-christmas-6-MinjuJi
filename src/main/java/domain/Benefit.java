package domain;

import java.util.List;

public class Benefit {
    private static final int DISCOUNT_INDEX = 1;
    private final int christmasDiscount;
    private final List<Integer> weekDiscount;
    private final int specialDiscount;
    private final boolean champagne;

    public Benefit(int christmasDiscount, List<Integer> weekDiscount, int specialDiscount, boolean champagne) {
        this.christmasDiscount = christmasDiscount;
        this.weekDiscount = weekDiscount;
        this.specialDiscount = specialDiscount;
        this.champagne = champagne;
    }

    public int calculateTotalDiscount() {
        return christmasDiscount + weekDiscount.get(DISCOUNT_INDEX) + specialDiscount;
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public List<Integer> getWeekDiscount() {
        return weekDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public boolean hasChampagne() {
        return champagne;
    }

}
