package models;

import java.io.Serializable;
import java.util.Date;

/**
 * Класс, представляющий организацию.
 * Реализует интерфейс Comparable для сортировки по ID и Serializable для сохранения в файл.
 * <p>
 * Поля id и creationDate генерируются автоматически.
 */
public class Organization implements Comparable<Organization>, Serializable {

    /**
     * Уникальный идентификатор организации.
     * Значение должно быть больше 0 и генерируется автоматически.
     */
    private int id;

    /**
     * Название организации.
     * Не может быть null или пустой строкой.
     */
    private String name;

    /**
     * Координаты расположения организации.
     * Не могут быть null.
     */
    private Coordinates coordinates;

    /**
     * Дата и время создания записи об организации.
     * Генерируется автоматически при создании объекта.
     */
    private Date creationDate;

    /**
     * Годовой оборот организации.
     * Значение должно быть строго больше 0.
     */
    private long annualTurnover;

    /**
     * Полное официальное название организации.
     * Не может быть null или пустой строкой.
     */
    private String fullName;

    /**
     * Тип организации (например, государственная, частная и т.д.).
     * Может принимать значение null.
     */
    private OrganizationType type;

    /**
     * Почтовый адрес организации.
     * Не может быть null.
     */
    private Address postalAddress;

    /**
     * Статический счётчик для генерации уникальных ID.
     */
    private static int nextId = 1;

    /**
     * Устанавливает следующее значение ID для генератора.
     * Используется после загрузки коллекции из файла, чтобы избежать дублирования ID.
     *
     * @param nextId следующее свободное значение ID
     */
    public static void setNextId(int nextId) {
        Organization.nextId = nextId;
    }

    /**
     * Конструктор без параметров.
     * Требуется для корректной работы XML-парсера при загрузке данных из файла.
     */
    public Organization() {
    }

    /**
     * Конструктор для создания новой организации с заданными параметрами.
     * ID и дата создания генерируются автоматически.
     *
     * @param name          название организации
     * @param coordinates   координаты организации
     * @param annualTurnover годовой оборот
     * @param fullName      полное название организации
     * @param type          тип организации (может быть null)
     * @param postalAddress почтовый адрес
     * @throws IllegalArgumentException если обязательные поля некорректны
     */
    public Organization(String name, Coordinates coordinates, long annualTurnover, String fullName, OrganizationType type, Address postalAddress) {
        this.id = nextId++;
        this.creationDate = new Date();
        setName(name);
        this.coordinates = coordinates;
        setAnnualTurnover(annualTurnover);
        setFullName(fullName);
        this.type = type;
        setPostalAddress(postalAddress);
    }

    /**
     * Возвращает уникальный идентификатор организации.
     *
     * @return ID организации
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор организации.
     * Проверяет, что ID больше 0.
     *
     * @param id новый идентификатор
     * @throws IllegalArgumentException если id <= 0
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID должен быть больше 0!");
        }
        this.id = id;
    }

    /**
     * Устанавливает дату создания организации.
     * Обычно используется только при загрузке из файла.
     *
     * @param creationDate дата создания
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Возвращает название организации.
     *
     * @return название организации
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название организации.
     * Проверяет, что название не null и не пустая строка.
     *
     * @param name новое название
     * @throws IllegalArgumentException если name null или пустая строка
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть null или пустым!");
        }
        this.name = name.trim();
    }

    /**
     * Возвращает координаты организации.
     *
     * @return объект Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Устанавливает координаты организации.
     * Проверяет, что координаты не null.
     *
     * @param coordinates новые координаты
     * @throws IllegalArgumentException если coordinates null
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null!");
        }
        this.coordinates = coordinates;
    }

    /**
     * Возвращает дату создания организации.
     *
     * @return дата создания
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает годовой оборот организации.
     *
     * @return годовой оборот
     */
    public long getAnnualTurnover() {
        return annualTurnover;
    }

    /**
     * Устанавливает годовой оборот организации.
     * Проверяет, что оборот строго больше 0.
     *
     * @param annualTurnover новый годовой оборот
     * @throws IllegalArgumentException если annualTurnover <= 0
     */
    public void setAnnualTurnover(long annualTurnover) {
        if (annualTurnover <= 0) {
            throw new IllegalArgumentException("Годовой оборот не может быть меньше или равен 0!");
        }
        this.annualTurnover = annualTurnover;
    }

    /**
     * Возвращает полное название организации.
     *
     * @return полное название
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Устанавливает полное название организации.
     * Проверяет, что название не null и не пустая строка.
     *
     * @param fullName новое полное название
     * @throws IllegalArgumentException если fullName null или пустая строка
     */
    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Полное имя кампании не может быть null или пустым!");
        }
        this.fullName = fullName.trim();
    }

    /**
     * Возвращает тип организации.
     *
     * @return тип организации или null
     */
    public OrganizationType getType() {
        return type;
    }

    /**
     * Устанавливает тип организации.
     *
     * @param type новый тип организации (может быть null)
     */
    public void setType(OrganizationType type) {
        this.type = type;
    }

    /**
     * Возвращает почтовый адрес организации.
     *
     * @return почтовый адрес
     */
    public Address getPostalAddress() {
        return postalAddress;
    }

    /**
     * Устанавливает почтовый адрес организации.
     * Проверяет, что адрес не null.
     *
     * @param postalAddress новый почтовый адрес
     * @throws IllegalArgumentException если postalAddress null
     */
    public void setPostalAddress(Address postalAddress) {
        if (postalAddress == null) {
            throw new IllegalArgumentException("Почтовый адрес не может быть null!");
        }
        this.postalAddress = postalAddress;
    }

    /**
     * Сравнивает две организации по их ID.
     * Используется для сортировки коллекции.
     *
     * @param other другая организация для сравнения
     * @return отрицательное число, если текущий ID меньше; 0, если равны; положительное, если больше
     */
    @Override
    public int compareTo(Organization other) {
        return Integer.compare(this.id, other.id);
    }

    /**
     * Возвращает строковое представление организации.
     * Включает все поля объекта в читаемом формате.
     *
     * @return строка с описанием организации
     */
    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", annualTurnover=" + annualTurnover +
                ", fullName='" + fullName + '\'' +
                ", type=" + type +
                ", postalAddress=" + postalAddress +
                '}';
    }
}