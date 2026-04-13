package models;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
* Типы организаций
*/

public enum OrganizationType {
    PUBLIC,
    GOVERNMENT,
    TRUST,
    PRIVATE_LIMITED_COMPANY;

    /**
     * Преобразование строки в константу из перечисления
     * @param value строка для преобразования
     * @return одну из констант из OrganizationType или null, если преобразование невозможно
     */
    public static OrganizationType fromString (String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        try {
            return OrganizationType.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

        /**
         * возвращает список допустимыз значений в качестве подсказки пользователю
         * @return список констант
         */
        public static String getAvailableValues() {
            return Arrays.stream(values()).map(Enum::name).collect(Collectors.joining(", "));
        }
}