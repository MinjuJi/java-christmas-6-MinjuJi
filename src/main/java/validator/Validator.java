package validator;

import domain.Menu;

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
            throw new IllegalArgumentException("1 이상 31 이하로 입력 해주세요.");
        }
    }

    public static void validateExistingMenu(String[] menuAndCount) {
        String menuName = menuAndCount[MENU_INDEX];
        for (Menu menu : Menu.values()) {
            if (!menu.name().equals(menuName)) {
                throw new IllegalArgumentException("메뉴판에 있는 메뉴를 입력 해주세요.");
            }
        }
    }

}
