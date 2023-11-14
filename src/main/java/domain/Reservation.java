package domain;

import java.util.Map;

public class Reservation {
    private int day;
    private Map<Menu, Integer> order;

    public Reservation(int day, Map<Menu, Integer> order) {
        this.day = day;
        this.order = order;
    }

    public int getDay() {
        return day;
    }

    public Map<Menu, Integer> getOrder() {
        return order;
    }
}
