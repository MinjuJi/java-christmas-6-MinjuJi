package view;

import domain.Badge;
import domain.Benefit;
import domain.Menu;
import domain.Reservation;
import java.util.Map;
import service.Event;

public class OutputView {
    public void printPreviewBenefit(Reservation reservation) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", reservation.getDay());
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

    public void printGift(Benefit benefit) {
        System.out.println("\n<증정 메뉴>");
        if (benefit.isChampagne()) {
            System.out.println("샴페인 1개");
        }
        System.out.println("없음");
    }

    public void printBenefitDetails(Benefit benefit) {
        System.out.println("\n<혜택 내역>");
        if (benefit.calculateTotalDiscount() == 0 && !benefit.isChampagne()) {
            System.out.println("없음");
        }
        if (benefit.getChristmasDiscount() > 0) {
            System.out.printf("크리스마스 디데이 할인: -&d원\n", benefit.getSpecialDiscount());
        }
        if (benefit.getWeekdayDiscount() > 0) {
            System.out.printf("평일 할인: -&d원\n", benefit.getWeekdayDiscount());
        }
        if (benefit.getWeekendDiscount() > 0) {
            System.out.printf("주말 할인: -&d원\n", benefit.getWeekendDiscount());
        }
        if (benefit.getSpecialDiscount() > 0) {
            System.out.printf("특별 할인: -&d원\n", benefit.getSpecialDiscount());
        }
        if (benefit.isChampagne()) {
            System.out.println("증정 이벤트: -25,000원");
        }
    }

    public void printTotalBenefit(Benefit benefit, Event event) {
        System.out.println("\n<총혜택 금액>");
        int totalDiscount = benefit.calculateTotalDiscount();
        boolean isChampagne = benefit.isChampagne();
        System.out.printf("-%,d원\n", event.calculateTotalBenefit(totalDiscount, isChampagne));
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
        if (badge.getBadge() == null) {
            System.out.println("없음");
        }
        System.out.println(badge.getBadge());
    }
}