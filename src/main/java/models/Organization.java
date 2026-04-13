package models;
import java.io.Serializable;
import java.util.Date;

public class Organization implements Comparable<Organization>, Serializable{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long annualTurnover; //Значение поля должно быть больше 0
    private String fullName; //Поле не может быть null
    private OrganizationType type; //Поле может быть null
    private Address postalAddress; //Поле не может быть null

    private static int nextId = 1;

    public static void setNextId(int nextId){
        Organization.nextId = nextId;
    }

    public Organization(){}

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

    public int getId() {
        return id;
    }
    public void setId(int id){
        if(id <= 0){
            throw new IllegalArgumentException("ID должен быть больше 0!");
        }
        this.id = id;
    }

    public void setCreationDate(java.util.Date creationDate){
        this.creationDate = creationDate;
    }
    public String getName (){
        return name;
    }

    public void setName(String name){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Имя не может быть null или пустым!");
        }
        this.name = name.trim();
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates){
        if (coordinates == null){
            throw new IllegalArgumentException("Координаты не могут быть null!");
        }
        this.coordinates = coordinates;
    }

    public Date getCreationDate(){
        return creationDate;
    }

    public long getAnnualTurnover(){
        return annualTurnover;
    }
    public void setAnnualTurnover(long annualTurnover){
        if (annualTurnover <=0){
            throw new IllegalArgumentException("Годовой оборот не может быть меньше 0!");
        }
        this.annualTurnover = annualTurnover;
    }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Полное имя кампании не может быть null или пустым!");
        }
        this.fullName = fullName.trim();
    }

    public OrganizationType getType() { return type; }

    public void setType(OrganizationType type) { this.type = type; }

    public Address getPostalAddress() { return postalAddress; }

    public void setPostalAddress(Address postalAddress) {
        if (postalAddress == null) {
            throw new IllegalArgumentException("Почтовый адрес не может быть null!");
        }
        this.postalAddress = postalAddress;
    }

    @Override
    public int compareTo(Organization other) {
        return Integer.compare(this.id, other.id);
    }

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