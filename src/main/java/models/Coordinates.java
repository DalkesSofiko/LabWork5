package models;
import java.io.Serializable;

/**
 * Координаты нахождения организации
 */
public class Coordinates {
    private long x; //Значение поля должно быть больше -410
    private double y;

    public Coordinates(){}

    public Coordinates(long x, double y){
        setX(x);
        this.y = y;
    }

    public void setX(long x){
        if (x <= -410){
            throw new IllegalArgumentException("Координата X должна быть больше -410!");
        }
        this.x = x;
    }
    public long getX(){
        return x;
    }

    public void setY(double y){
        this.y = y;
    }
    public double getY(){
        return y;
    }

    @Override
    public String toString(){
        return "Координаты организации: (" + x + "; " + y + ")";
    }

}
