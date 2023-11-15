package validator;

import domain.Menu;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.Utils;

public class Validator {
    private static final String ONLY_NUMBER_REGEX = "^[0-9]*$";
    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private static final int MENU_INDEX = 0;
    private static final int COUNT_ZERO = 0;
    private static final int MIN_TOTAL_MENU_COUNT = 1;
    private static final int MAX_TOTAL_MENU_COUNT = 20;
    private static final String ORDER_FORMAT_REGEX = "^[가-힣]+-[0-9]+$";
    private static final String MENU_AND_COUNT_DELIMITER = "\\s*-\\s*";

    public static void validateInputIsNumeric(String input) {
        if (!input.matches(ONLY_NUMBER_REGEX)) {
            throw new IllegalArgumentException("숫자만 입력해 주세요.");
        }
    }

    public static void validateDataInRange(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new IllegalArgumentException("방문 날짜를 1 이상 31 이하로 입력해 주세요.");
        }
    }

    public static void validateExistingMenu(String[] menuAndCount) {
        String menuName = menuAndCount[MENU_INDEX];
        List<String> existingMenu = new ArrayList<>();
        for (Menu menu : Menu.values()) {
            String existingMenuName = menu.name();
            existingMenu.add(existingMenuName);
        }
        if (!existingMenu.contains(menuName)) {
            throw new IllegalArgumentException("메뉴판에 있는 메뉴를 입력해 주세요.");
        }
    }

    public static void validateMenuCountInRange(Map<Menu, Integer> order) {
        int totalMenuCount = COUNT_ZERO;
        for (Map.Entry<Menu, Integer> menuCount : order.entrySet()) {
            totalMenuCount += menuCount.getValue();
        }
        if (totalMenuCount < MIN_TOTAL_MENU_COUNT || totalMenuCount > MAX_TOTAL_MENU_COUNT) {
            throw new IllegalArgumentException("주문 메뉴의 총 개수를 1 이상 20 이하로 입력해 주세요.");
        }
    }

    public static void validateOrderFormat(String[] separatedOrders) {
        for (String orderFormat : separatedOrders) {
            if (!orderFormat.matches(ORDER_FORMAT_REGEX)) {
                throw new IllegalArgumentException("[해산물파스타-2,레드와인-1,초코케이크-1] 형식으로 입력해 주세요.");
            }
        }
    }

    public static void validateDuplicatedMenu(String[] separatedOrders) {
        Set<String> menu = new HashSet<>();
        for (String separatedOrder : separatedOrders) {
            String[] menuAndCount = Utils.splitInput(separatedOrder, MENU_AND_COUNT_DELIMITER);
            menu.add(menuAndCount[MENU_INDEX]);
        }
        if (separatedOrders.length != menu.size()) {
            throw new IllegalArgumentException("메뉴를 중복되지 않게 입력해 주세요");
        }
    }

    public static void validateOnlyBeverages(Map<Menu, Integer> order) {
        Set<Menu> menu = order.keySet();
        menu.remove(Menu.제로콜라);
        menu.remove(Menu.레드와인);
        menu.remove(Menu.샴페인);
        if (menu.isEmpty()) {
            throw new IllegalArgumentException("음료만 주문 시, 주문할 수 없습니다.");
        }
    }
}
