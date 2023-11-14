package view;

import camp.nextstep.edu.missionutils.Console;
import domain.Menu;
import java.util.HashMap;
import java.util.Map;
import utils.Utils;

public class InputView {
    private static final int MENU_INDEX = 0;
    private static final int COUNT_INDEX = 1;
    private static final String firstDelimiter = "\\s*,\\s*";
    private static final String secondDelimiter = "\\s*-\\s*";

    public int readDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String day = Console.readLine();
        return Integer.parseInt(day);
    }

    public Map<Menu, Integer> readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        Map<Menu, Integer> order = new HashMap<>();
        String[] separatedOrders = Utils.splitInput(Console.readLine(), firstDelimiter);
        for (String separatedOrder : separatedOrders) {
            String[] menuAndCount = Utils.splitInput(separatedOrder, secondDelimiter);
            Menu menu = Menu.valueOf(menuAndCount[MENU_INDEX]);
            Integer count = Integer.valueOf(menuAndCount[COUNT_INDEX]);
            order.put(menu, count);
        }
        return order;
    }
}