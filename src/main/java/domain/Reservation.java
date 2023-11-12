package domain;

import java.util.Map;
import java.util.Set;

public class Reservation {
    private int date;
    private Map<String, Integer> order;

    public Reservation(int date, Map<String, Integer> order) {
        this.date = date;
        this.order = order;
    }

    public int getDate() {
        return date;
    }

    public Set<Map.Entry<String, Integer>> getOrder() {
        return order.entrySet();
    }
}
