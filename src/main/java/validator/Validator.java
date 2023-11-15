package validator;

public class Validator {
    private static final String ONLY_NUMBER_REGEX = "^[0-9]*$";

    public static void validateInputIsNumeric(String input) {
        if (!input.matches(ONLY_NUMBER_REGEX)) {
            throw new IllegalArgumentException("숫자만 입력해 주세요.");
        }
    }

}
