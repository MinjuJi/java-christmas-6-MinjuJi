package view;

import domain.Badge;
import domain.Benefit;
import domain.Menu;
import domain.Reservation;
import java.util.List;
import java.util.Map;
import service.Event;

public class OutputView {
    private static final int WEEKDAY_OR_WEEKEND_INDEX = 0;
    private static final int WEEKDAY_INDEX = 0;
    private static final int WEEKEND_INDEX = 1;
    private static final int NO_BENEFIT = 0;

    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printPreviewMessage(Reservation reservation) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", reservation.getDay());
    }

    public void printReservationMenu(Reservation reservation) {
        System.out.println("\n<주문 메뉴>");
        Map<Menu, Integer> order = reservation.getOrder();
        for (Map.Entry<Menu, Integer> menuAndCount : order.entrySet()) {
            System.out.printf("%s %d개\n", menuAndCount.getKey(), menuAndCount.getValue());
        }
    }

    public void printTotalPriceBeforeDiscount(Reservation reservation, Event event) {
        System.out.println("\n<할인 전 총주문 금액>");
        Map<Menu, Integer> order = reservation.getOrder();
        int totalPrice = event.calculateTotalPriceBeforeDiscount(order);
        System.out.printf("%,d원\n", totalPrice);
    }

    public void printHasGift(Benefit benefit) {
        System.out.println("\n<증정 메뉴>");
        if (benefit.isChampagne()) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    public void printBenefitDetails(Benefit benefit) {
        System.out.println("\n<혜택 내역>");
        if (benefit.calculateTotalDiscount() == 0 && !benefit.isChampagne()) {
            System.out.println("없음");
            return;
        }
        printChristmasDiscount(benefit);
        printWeekDiscount(benefit);
        printSpecialDiscount(benefit);
        printGift(benefit);
    }

    public void printChristmasDiscount(Benefit benefit) {
        if (benefit.getChristmasDiscount() > 0) {
            System.out.printf("크리스마스 디데이 할인: -%,d원\n", benefit.getChristmasDiscount());
        }
    }

    public void printWeekDiscount(Benefit benefit) {
        List<Integer> weekDiscount = benefit.getWeekDiscount();
        if (weekDiscount.get(WEEKDAY_OR_WEEKEND_INDEX) == WEEKDAY_INDEX) {
            System.out.printf("평일 할인: -%,d원\n", weekDiscount.get(1));
        }
        if (weekDiscount.get(WEEKDAY_OR_WEEKEND_INDEX) == WEEKEND_INDEX) {
            System.out.printf("주말 할인: -%,d원\n", weekDiscount.get(1));
        }
    }

    public void printSpecialDiscount(Benefit benefit) {
        if (benefit.getSpecialDiscount() > 0) {
            System.out.printf("특별 할인: -%,d원\n", benefit.getSpecialDiscount());
        }
    }

    public void printGift(Benefit benefit) {
        if (benefit.isChampagne()) {
            System.out.println("증정 이벤트: -25,000원");
        }
    }

    public void printTotalBenefit(Benefit benefit, Event event) {
        System.out.println("\n<총혜택 금액>");
        int totalDiscount = benefit.calculateTotalDiscount();
        boolean isChampagne = benefit.isChampagne();
        int totalBenefit = event.calculateTotalBenefit(totalDiscount, isChampagne);
        if (totalBenefit == NO_BENEFIT) {
            System.out.println("없음");
            return;
        }
        System.out.printf("-%,d원\n", totalBenefit);
    }

    public void printExpectedPaymentAmount(Reservation reservation, Benefit benefit, Event event) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        Map<Menu, Integer> order = reservation.getOrder();
        int totalPriceBeforeDiscount = event.calculateTotalPriceBeforeDiscount(order);
        int totalDiscount = benefit.calculateTotalDiscount();
        int expectedPaymentAmount = event.calculateExpectedPaymentAmount(totalPriceBeforeDiscount, totalDiscount);
        System.out.printf("%,d원\n", expectedPaymentAmount);
    }

    public void printBadge(Badge badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge.getBadge());
    }
}