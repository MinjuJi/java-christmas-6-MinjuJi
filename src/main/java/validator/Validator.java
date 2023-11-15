package validator;

import domain.Menu;
import java.util.Map;

public class Validator {
    private static final String ONLY_NUMBER_REGEX = "^[0-9]*$";
    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private static final int MENU_INDEX = 0;

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
        for (Menu menu : Menu.values()) {
            if (!menu.name().equals(menuName)) {
                throw new IllegalArgumentException("메뉴판에 있는 메뉴를 입력해 주세요.");
            }
        }
    }

    public static void validateMenuCountInRange(Map<Menu, Integer> order) {
        int totalMenuCount = 0;
        for (Map.Entry<Menu, Integer> menuCount : order.entrySet()) {
            totalMenuCount += menuCount.getValue();
        }
        if (totalMenuCount < 1 || totalMenuCount > 20) {
            throw new IllegalArgumentException("주문 메뉴의 총 개수를 1 이상 20 이하로 입력해 주세요.");
        }
    }

}
