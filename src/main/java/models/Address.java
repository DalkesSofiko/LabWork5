package models;
import java.io.Serializable;

/** Адрес организации*/

public class Address implements Serializable{
    private String street; //Поле может быть null

    /** пустой конструктор для будущего XML-парсера*/
    public Address(){}

    public Address(String street){
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString(){
        if (street == null){
            return null;
        }
        else{
            return street;
        }
    }
}