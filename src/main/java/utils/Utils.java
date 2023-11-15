package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Utils {
    public static String[] splitInput(String input, String delimiter) {
        return input.split(delimiter);
    }

    public static int subtract(int num1, int num2) {
        return num1 - num2;
    }

    public static DayOfWeek findDayOfWeek(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        return date.getDayOfWeek();
    }

    public static LocalDate createLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }
}
