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
        int day = inputDay();
        try {
            Map<Menu, Integer> order = inputOrder();
            reservation = new Reservation(day, order);
        } catch (IllegalArgumentException error) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            Map<Menu, Integer> order = inputOrder();
            reservation = new Reservation(day, order);
        }
    }

    private int inputDay() {
        try {
            return inputView.readDay();
        } catch (IllegalArgumentException error) {
            System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            return inputDay();
        }
    }

    private Map<Menu, Integer> inputOrder() {
        try {
            return inputView.readOrder();
        } catch (IllegalArgumentException error) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            return inputOrder();
        }
    }

    private void applyEvent() {
        benefit = event.initializeBenefit(reservation);
        badge = event.offerBadgeByTotalBenefit(benefit.calculateTotalDiscount(), benefit.hasChampagne());
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
