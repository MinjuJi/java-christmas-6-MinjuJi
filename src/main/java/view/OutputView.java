package view;

import domain.Menu;
import domain.Reservation;
import java.util.Map;

public class OutputView {
    public void printPreviewBenefit(Reservation reservation) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", reservation.getDay());
    }

    public void printReservationMenu(Reservation reservation) {
        System.out.println("<주문 메뉴>");
        Map<Menu, Integer> order = reservation.getOrder();
        for (Map.Entry<Menu, Integer> menuAndCount : order.entrySet()) {
            System.out.printf("%s %d개", menuAndCount.getKey(), menuAndCount.getValue());
        }
    }

}
