package model;

public class Motorbike extends Vehicle {

    int speed;
    boolean license;

    public Motorbike() {
        super();
    }

    public Motorbike(String id) {
        super(id);
    }

    public Motorbike(int speed, boolean license) {
        this.speed = speed;
        this.license = license;
    }

    public Motorbike(int speed, boolean license, String id, String name, String color, int price, String brand) {
        super(id, name, color, price, brand);
        this.speed = speed;
        this.license = license;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isLicense() {
        return license;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setLicense(boolean license) {
        this.license = license;
    }

    public void makeSound() {
        System.out.println("Tin tin tin");
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "Speed: " + speed + "\n%75s%-20s\n", " ", 
                             ("Require license: " + ((license) ? "Yes" : "No")));
    }
}
