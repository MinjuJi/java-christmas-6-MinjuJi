package controller;

import domain.Badge;
import domain.Benefit;
import domain.Menu;
import domain.Reservation;
import java.util.Map;
import service.Event;
import view.InputView;

public class Controller {
    private final InputView inputView = new InputView();
    private final Event event = new Event();
    private Reservation reservation;
    private Benefit benefit;
    private Badge badge;

    public void run() {
        reserve();
        applyEvent();
    }

    private void reserve() {
        int day = inputView.readDay();
        Map<Menu, Integer> order = inputView.readMenu();
        reservation = new Reservation(day, order);
    }

    private void applyEvent() {
        benefit = event.initializeBenefit(reservation);
        badge = event.offerBadgeByTotalBenefit(benefit.calculateTotalDiscount(), benefit.isChampagne());
    }

}
