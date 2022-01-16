package model;

public class Vehicle implements Comparable {

    String id;  // Format: Vxxx, x: number 0-9
    String name;
    String color;
    int price;
    String brand;

    public Vehicle() {
    }

    public Vehicle(String id) {
        this.id = id;
    }

    public Vehicle(String id, String name, String color, int price, String brand) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return String.format(id + " | %-20s | %-10s | $%-10d | %-15s | ", 
                             name, color, price, brand);
    }

    // Sort in the descending order of ID
    @Override
    public int compareTo(Object vehicle) {
        return this.getId().compareTo(((Vehicle) vehicle).getId());
    }
}
