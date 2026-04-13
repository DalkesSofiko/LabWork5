package utils;

import Main.InputCancelledException;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Утилитарный класс для ввода и валидации данных.
 */
public class InputHelper {

    /**
     * Читает строку с проверкой на пустоту.
     * @param sc сканер
     * @param prompt приглашение к вводу
     * @param nullable можно ли оставить пустым (вернуть null)
     * @return введённая строка или null
     */
    public static String readString(Scanner sc, String prompt, boolean nullable) {
        String Input1 = sc.nextLine().trim();

        if (Input1.equalsIgnoreCase("cancel") || Input1.equalsIgnoreCase("отмена")) {
            throw new InputCancelledException();
        }

        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty() && nullable) return null;
            if (!input.isEmpty()) return input;
            System.out.println("Поле не может быть пустым.");
        }
    }

    /**
     * Читает long с проверкой границ.
     */
    public static long readLong(Scanner sc, String prompt, long minExclusive, Long maxInclusive) {
        while (true) {
            System.out.print(prompt);
            String Input1 = sc.nextLine().trim();

            if (Input1.equalsIgnoreCase("cancel") || Input1.equalsIgnoreCase("отмена")) {
                throw new InputCancelledException();
            }
            try {
                long val = Long.parseLong(sc.nextLine().trim());
                if (val <= minExclusive) { System.out.println("Значение должно быть > " + minExclusive); continue; }
                if (maxInclusive != null && val > maxInclusive) { System.out.println("Значение должно быть <= " + maxInclusive); continue; }
                return val;
            } catch (NumberFormatException e) { System.out.println("Ожидается целое число."); }
        }
    }

    /**
     * Читает double.
     */
    public static double readDouble(Scanner sc, String prompt) {
        String Input1 = sc.nextLine().trim();

        if (Input1.equalsIgnoreCase("cancel") || Input1.equalsIgnoreCase("отмена")) {
            throw new InputCancelledException();
        }

        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Ожидается число."); }
        }
    }

    /**
     * Читает enum или null.
     */
    public static <T extends Enum<T>> T readEnum(Scanner sc, String prompt, Class<T> cls) {
        System.out.println("Доступные значения: " + Arrays.toString(cls.getEnumConstants()));
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) return null;
            try { return Enum.valueOf(cls, input.toUpperCase()); }
            catch (IllegalArgumentException e) { System.out.println("Некорректное значение enum."); }
        }
    }
}