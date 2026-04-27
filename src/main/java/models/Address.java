package models;

import java.io.Serializable;

/**
 * Класс, представляющий почтовый адрес организации.
 * Реализует интерфейс Serializable для сохранения в файл.
 */
public class Address implements Serializable {

    /**
     * Улица и номер дома.
     * Поле может принимать значение null.
     */
    private String street;

    /**
     * Конструктор без параметров.
     * Требуется для корректной работы XML-парсера при загрузке данных из файла.
     */
    public Address() {
    }

    /**
     * Конструктор для создания адреса с заданной улицей.
     * @param street название улицы (может быть null)
     */
    public Address(String street) {
        this.street = street;
    }

    /**
     * Возвращает название улицы.
     * @return название улицы или null, если не указано
     */
    public String getStreet() {
        return street;
    }

    /**
     * Устанавливает новое название улицы.
     * @param street новое название улицы (может быть null)
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Возвращает строковое представление адреса.
     * Если улица не указана (null), возвращает null.
     * @return название улицы или null
     */
    @Override
    public String toString() {
        if (street == null) {
            return null;
        } else {
            return street;
        }
    }
}