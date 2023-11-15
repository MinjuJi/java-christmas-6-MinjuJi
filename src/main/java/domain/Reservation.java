package domain;

import java.util.Map;
import validator.Validator;

public class Reservation {
    private int day;
    private Map<Menu, Integer> order;

    public Reservation(int day, Map<Menu, Integer> order) throws IllegalArgumentException {
        this.day = day;
        validateOrder(order);
        this.order = order;
    }

    private void validateOrder(Map<Menu, Integer> order) {
        Validator.validateMenuCountInRange(order);
        Validator.validateOnlyBeverages(order);
    }

    public int getDay() {
        return day;
    }

    public Map<Menu, Integer> getOrder() {
        return order;
    }
}
