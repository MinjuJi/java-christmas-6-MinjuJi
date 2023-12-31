package view;

import camp.nextstep.edu.missionutils.Console;
import domain.Menu;
import java.util.HashMap;
import java.util.Map;
import utils.Utils;
import validator.Validator;

public class InputView {
    private static final int MENU_INDEX = 0;
    private static final int COUNT_INDEX = 1;
    private static final String ORDER_DELIMITER = "\\s*,\\s*";
    private static final String MENU_AND_COUNT_DELIMITER = "\\s*-\\s*";

    public int readDay() throws IllegalArgumentException {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String day = Console.readLine();
        Validator.validateDay(day);
        return Integer.parseInt(day);
    }

    public Map<Menu, Integer> readOrder() throws IllegalArgumentException {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        Map<Menu, Integer> order = new HashMap<>();
        String[] separatedOrders = Utils.splitInput(Console.readLine(), ORDER_DELIMITER);
        Validator.validateOrderFormat(separatedOrders);
        Validator.validateDuplicatedMenu(separatedOrders);
        for (String separatedOrder : separatedOrders) {
            String[] menuAndCount = Utils.splitInput(separatedOrder, MENU_AND_COUNT_DELIMITER);
            Validator.validateExistingMenu(menuAndCount);
            Menu menu = Menu.valueOf(menuAndCount[MENU_INDEX]);
            Integer count = Integer.valueOf(menuAndCount[COUNT_INDEX]);
            order.put(menu, count);
        }
        return order;
    }
}