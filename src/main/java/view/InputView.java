package view;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.Map;
import utils.Utils;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String date = Console.readLine();
        return Integer.parseInt(date);
    }

    public Map<String, Integer> readMenu(String firstDelimiter, String secondDelimiter) {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        Map<String, Integer> orderDetails = new HashMap<>();
        String[] order = Utils.splitInput(Console.readLine(), firstDelimiter);
        for (String separatedOrder : order) {
            String[] menuAndNumber = Utils.splitInput(separatedOrder, secondDelimiter);
            String menuName = menuAndNumber[0];
            Integer menuCount = Integer.valueOf(menuAndNumber[1]);
            orderDetails.put(menuName, menuCount);
        }
        return orderDetails;
    }
}