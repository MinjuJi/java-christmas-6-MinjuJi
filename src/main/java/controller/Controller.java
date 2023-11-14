package controller;

import domain.Badge;
import domain.Benefit;
import domain.Menu;
import domain.Reservation;
import java.util.Map;
import service.Event;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Event event = new Event();
    private Reservation reservation;
    private Benefit benefit;
    private Badge badge;

    public void run() {
        reserve();
        applyEvent();
        showReservationAndBenefits();
    }

    private void reserve() {
        outputView.printWelcomeMessage();
        int day = inputView.readDay();
        Map<Menu, Integer> order = inputView.readMenu();
        reservation = new Reservation(day, order);
    }

    private void applyEvent() {
        benefit = event.initializeBenefit(reservation);
        badge = event.offerBadgeByTotalBenefit(benefit.calculateTotalDiscount(), benefit.isChampagne());
    }

    private void showReservationAndBenefits() {
        outputView.printPreviewMessage(reservation);
        outputView.printReservationMenu(reservation);
        outputView.printTotalPriceBeforeDiscount(reservation, event);
        outputView.printHasGift(benefit);
        outputView.printBenefitDetails(benefit);
        outputView.printTotalBenefit(benefit, event);
        outputView.printExpectedPaymentAmount(reservation, benefit, event);
        outputView.printBadge(badge);
    }
}
