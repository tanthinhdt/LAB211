package model;

public class Car extends Vehicle {

    String type;
    int yearManufacture;

    public Car() {
        super();
    }

    public Car(String id) {
        super(id);
    }

    public Car(String type, int yearManufacture) {
        this.type = type;
        this.yearManufacture = yearManufacture;
    }

    public Car(String type, int yearManufacture, String id, String name, String color, int price, String brand) {
        super(id, name, color, price, brand);
        this.type = type;
        this.yearManufacture = yearManufacture;
    }

    public String getType() {
        return type;
    }

    public int getYearManufacture() {
        return yearManufacture;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYearManufacture(int yearManufacture) {
        this.yearManufacture = yearManufacture;
    }
    
    @Override
    public String toString() {
        return String.format(super.toString() + "Type: " + type + "\n%100s\n", 
                             ("Year of manufacture: " + yearManufacture));
    }
}
