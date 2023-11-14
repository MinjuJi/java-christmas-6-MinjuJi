package domain;

import java.util.List;

public class Benefit {

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
        return christmasDiscount + weekDiscount.get(1) + specialDiscount;
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

    public boolean isChampagne() {
        return champagne;
    }

}
