package models;

import java.io.Serializable;

/**
 * Класс, представляющий координаты расположения организации.
 * Реализует интерфейс Serializable для сохранения в файл.
 */
public class Coordinates implements Serializable {

    /**
     * Координата X.
     * Значение должно быть строго больше -410.
     */
    private long x;

    /**
     * Координата Y.
     * Не имеет ограничений по значению.
     */
    private double y;

    /**
     * Конструктор без параметров.
     * Требуется для корректной работы XML-парсера при загрузке данных из файла.
     */
    public Coordinates() {
    }

    /**
     * Конструктор для создания координат с заданными значениями.
     * Проверяет валидность координаты X.
     *
     * @param x координата X (должна быть > -410)
     * @param y координата Y
     * @throws IllegalArgumentException если x <= -410
     */
    public Coordinates(long x, double y) {
        setX(x);
        this.y = y;
    }

    /**
     * Устанавливает новое значение координаты X.
     * Проверяет, что значение строго больше -410.
     *
     * @param x новое значение координаты X
     * @throws IllegalArgumentException если x <= -410
     */
    public void setX(long x) {
        if (x <= -410) {
            throw new IllegalArgumentException("Координата X должна быть больше -410!");
        }
        this.x = x;
    }

    /**
     * Возвращает значение координаты X.
     *
     * @return координата X
     */
    public long getX() {
        return x;
    }

    /**
     * Устанавливает новое значение координаты Y.
     *
     * @param y новое значение координаты Y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Возвращает значение координаты Y.
     *
     * @return координата Y
     */
    public double getY() {
        return y;
    }

    /**
     * Возвращает строковое представление координат.
     * Формат: "Координаты организации: (x; y)"
     *
     * @return строка с координатами
     */
    @Override
    public String toString() {
        return "Координаты организации: (" + x + "; " + y + ")";
    }
}