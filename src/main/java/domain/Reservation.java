package domain;

import java.util.Map;

public class Reservation {
    private int date;
    private Map<Menu, Integer> order;

    public Reservation(int date, Map<Menu, Integer> order) {
        this.date = date;
        this.order = order;
    }

    public int getDate() {
        return date;
    }

    public Map<Menu, Integer> getOrder() {
        return order;
    }
}
