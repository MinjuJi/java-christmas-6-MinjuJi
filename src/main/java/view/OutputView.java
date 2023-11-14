package view;

import domain.Benefit;
import domain.Menu;
import domain.Reservation;
import java.util.Map;
import service.Event;

public class OutputView {
    public void printPreviewBenefit(Reservation reservation) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", reservation.getDay());
    }

    public void printReservationMenu(Reservation reservation) {
        System.out.println("<주문 메뉴>");
        Map<Menu, Integer> order = reservation.getOrder();
        for (Map.Entry<Menu, Integer> menuAndCount : order.entrySet()) {
            System.out.printf("%s %d개\n", menuAndCount.getKey(), menuAndCount.getValue());
        }
    }

    public void printTotalPriceBeforeDiscount(Reservation reservation, Event event) {
        System.out.println("<할인 전 총주문 금액>");
        Map<Menu, Integer> order = reservation.getOrder();
        int totalPrice = event.calculateTotalPriceBeforeDiscount(order);
        System.out.printf("%,d원\n", totalPrice);
    }

    public void print(Benefit benefit) {
        System.out.println("<증정 메뉴>");
        if (benefit.isChampagne()) {
            System.out.println("샴페인 1개");
        }
        System.out.println("없음");
    }

}
