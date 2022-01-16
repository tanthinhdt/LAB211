package model;

import utils.Menu;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import utils.Inputter;

public class VehicleList extends Vector<Vehicle> {

    Scanner sc = new Scanner(System.in);

    // Validate ID of vehicle
    public String validateNewId(String id) throws Exception {
        if (!id.matches("^V\\d{3}$")) {
            throw new Exception("The ID's format: Vxxx");
        }
        if (searchById(id) >= 0) {
            throw new Exception("The ID is duplicated");
        }
        return id;
    }

    // Load vehicle from a file
    public void loadFromFile(String fname) {
        try {
            File f = new File(fname);
            if (!f.exists()) {
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String details;
            while ((details = br.readLine()) != null) {
                try {
                    StringTokenizer str = new StringTokenizer(details, ",");
                    int instance = Integer.parseInt(str.nextToken());
                    String id = validateNewId(str.nextToken());
                    String name = str.nextToken().toUpperCase();
                    String color = str.nextToken().toUpperCase();
                    int price = Inputter.validatePosInt(str.nextToken());
                    String brand = str.nextToken().toUpperCase();
                    // Get appropriate details for each type of vehicle
                    switch (instance) {
                        case 1:
                            String type = str.nextToken().toUpperCase();
                            int yearManufacture = Inputter.validateYear(str.nextToken());
                            this.add(new Car(type, yearManufacture, id, name, color, price, brand));
                            break;
                        case 2:
                            int speed = Inputter.validatePosInt(str.nextToken());
                            boolean license = (Inputter.validateBoolean(str.nextToken()));
                            this.add(new Motorbike(speed, license, id, name, color, price, brand));
                            break;
                        default:
                            throw new Exception();
                    }
                } catch (Exception e) {
                    // If the data of a line is false, it will be ignored
                    // Skip and continue to read next lines
                }
            }
            br.close();
            fr.close();
            System.out.println("Data has been loaded from " + fname);
            showVehicles();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }  
    }

    // Save all changes to file
    public void saveToFile(String fname) {
        try {
            File f = new File(fname);
            if (!f.exists()) {
                return;
            }
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Vehicle vehicle : this) {
                if (vehicle instanceof Car) {
                    pw.println("1," + vehicle.getId() + "," + vehicle.getName() + "," +
                            vehicle.getColor() + "," + vehicle.getPrice() + "," +
                            vehicle.getBrand() + "," + ((Car) vehicle).getType() + "," +
                            ((Car) vehicle).getYearManufacture());
                } else {
                    pw.println("2," + vehicle.getId() + "," + vehicle.getName() + "," +
                            vehicle.getColor() + "," + vehicle.getPrice() + "," +
                            vehicle.getBrand() + "," + ((Motorbike) vehicle).getSpeed() + "," +
                            (((Motorbike) vehicle).isLicense() ? "Yes" : "No"));
                }
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Data has been saved! Please check " + fname);
    }

    // Search a vehicle by ID
    public int searchById(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.elementAt(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    // Search vehicles by name in the descending order of ID
    public Vehicle[] searchByNameDesc(String name) {
        Vehicle[] foundList = new Vehicle[this.size()];
        int j = 0;
        for (int i = this.size()-1; i >= 0; i--) {
            if (this.elementAt(i).getName().equals(name)) {
                foundList[j] = this.elementAt(i);
                j++;
            }
        }
        return foundList;
    }

    // Search vehicle
    public void searchVehicle() {
        Menu menu = new Menu();
        menu.add("Search by Name");
        menu.add("Search by ID");
        int userChoice = menu.getUserSubChoice();

        switch (userChoice) {
            case 1:
                System.out.print("    Enter vehicle's name to search: ");
                String name = sc.nextLine().toUpperCase();
                System.out.println("Result: ");
                Vehicle[] foundList = searchByNameDesc(name);
                showVehicles(foundList);
                break;
            case 2:
                System.out.print("    Enter vehicle's ID to search: ");
                String id = sc.nextLine();
                System.out.println("Result: ");
                int pos = searchById(id);
                if (pos < 0) {
                    System.out.println("    This vehicle does not exist!!");
                } else {
                    showVehicle(this.elementAt(pos));
                }
        }
    }

    // Add new vehicles
    public void addNewVehicle() {
        Menu menu = new Menu();
        menu.add("Add a new car");
        menu.add("Add a new motorbike");
        menu.add("Back to main menu");
        int userChoice;

        do {
            userChoice = menu.getUserSubChoice();
            if (userChoice == 3) {
                return;
            }
            String id = "", name, color, brand;
            int price;
            boolean flag;
            // Input ID
            do {
                try {
                    System.out.print("    ID: ");
                    id = validateNewId(sc.nextLine());
                    flag = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    flag = true;
                }
            } while (flag);
            // Input other details
            System.out.print("    Name: ");
            name = sc.nextLine().toUpperCase();
            System.out.print("    Color: ");
            color = sc.nextLine().toUpperCase();
            price = Inputter.inputPosInt("    Price($): ");
            System.out.print("    Brand: ");
            brand = sc.nextLine().toUpperCase();
            // Input appropriate details for each type of vehicle
            switch (userChoice) {
                case 1:
                    System.out.print("    Type: ");
                    String type = sc.nextLine().toUpperCase();
                    int yearManufacture = Inputter.inputYear("    Year of manufacture: ");
                    this.add(new Car(type, yearManufacture, id, name, color, price, brand));
                    break;
                case 2:
                    int speed = Inputter.inputPosInt("    Speed: ");
                    boolean license = Inputter.inputBoolean("    Require license? (Y/N) ");
                    this.add(new Motorbike(speed, license, id, name, color, price, brand));
                    break;
            }
            System.out.println("    New vehicle added!\n");
        } while (userChoice == 1 || userChoice == 2);
    }

    // Update vehicle
    public void updateVehicle() {
        System.out.print("Enter vehicle's ID to update: ");
        String id = sc.nextLine();
        int pos = searchById(id);
        if (pos < 0) {
            System.out.println("This vehicle does not exist!");
            return;
        }

        Vehicle vehicle = this.elementAt(pos);
        String temp;
        
        System.out.println("Current details: ");
        showVehicle(vehicle);
        System.out.println("Enter new vehicle's details:");
        System.out.print("    Name: ");
        temp = sc.nextLine();
        if (!temp.isEmpty()) {
            vehicle.setName(temp.toUpperCase());
        }

        System.out.print("    Color: ");
        temp = sc.nextLine();
        if (!temp.isEmpty()) {
            vehicle.setColor(temp.toUpperCase());
        }

        temp = Inputter.inputPosIntString("    Price($): ");
        if (!temp.isEmpty()) {
            vehicle.setPrice(Integer.parseInt(temp));
        }

        System.out.print("    Brand: ");
        temp = sc.nextLine();
        if (!temp.isEmpty()) {
            vehicle.setBrand(temp.toUpperCase());
        }
        
        // Update appropriate details for each type of vehicle
        if (vehicle instanceof Car) {
            System.out.print("    Type: ");
            temp = sc.nextLine();
            if (!temp.isEmpty()) {
                ((Car) vehicle).setType(temp.toUpperCase());
            }

            temp = Inputter.inputYearString("    Year of manufacture: ");
            if (!temp.isEmpty()) {
                ((Car) vehicle).setYearManufacture(Integer.parseInt(temp));
            }
        } else {
            temp = Inputter.inputPosIntString("    Speed: ");
            if (!temp.isEmpty()) {
                ((Motorbike) vehicle).setSpeed(Integer.parseInt(temp));
            }

            temp = Inputter.inputBooleanString("    Require license (Y/N): ");
            if (!temp.isEmpty()) {
                ((Motorbike) vehicle).setLicense(temp.startsWith("Y") || temp.startsWith("y"));
            }
        }
        System.out.println("Updated details: ");
        showVehicle(vehicle);
    }

    // Delete a vehicle
    public void deleteVehicle() {
        System.out.print("Enter vehicle's ID to delete: ");
        String id = sc.nextLine();
        int pos = searchById(id);
        if (pos < 0) {
            System.out.println("This vehicle does not exist!");
            return;
        }
        if (Inputter.inputBoolean("Do you want to delete this vehicle? (Y/N) ")) {
            this.remove(pos);
            System.out.println("The vehicle " + id + " has been deleted!");
        }
        else {
            System.out.println("The vehicle " + id + " is kept!");
        }
    }

    // Print all vehicles' details
    public void printHeaders() {
        System.out.printf("%-4s | %-20s | %-10s | %-11s | %-15s | Other information\n",
        "ID", "Name", "Color", "Price ($)", "Brand");
        for (int i = 0; i <= 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    
    public void showVehicles() {
        Collections.sort(this);
        System.out.println("VEHICLE LIST");
        printHeaders();
        for (Vehicle vehicle : this) {
            System.out.println(vehicle);
        }
        System.out.println("TOTAL NUMBER OF VEHICLES: " + this.size());
    }

    public void showVehicles(Vehicle[] list) {
        System.out.println("VEHICLE LIST");
        printHeaders();
        int count = 0;
        for (Vehicle vehicle : list) {
            if (vehicle != null) {
                count++;
                System.out.println(vehicle);
            }
        }
        System.out.println("TOTAL NUMBER OF VEHICLES: " + count);
    }

    public void showVehicle(Vehicle vehicle) {
        printHeaders();
        System.out.println(vehicle);
    }

    // Print all vehicles' details in descending order of price
    public void showVehiclesDes() {
        Vehicle[] desList = new Vehicle[this.size()];
        for (int i = 0; i < this.size(); i++) {
            desList[i] = this.elementAt(i);
        }
        sort(desList);
        showVehicles(desList);
    }

    // Sort the list in the descending order of price (bubble sort)
    public Vehicle[] sort(Vehicle[] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length-i-1; j++) {
                if (list[j].getPrice() < list[j+1].getPrice()) {
                    Vehicle temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }
            }
        }
        return list;
    }

    public void showAllVehicles() {
        Menu menu = new Menu();
        menu.add("Show all vehicles");
        menu.add("Show all vehicles in descending order of price");
        int userChoice = menu.getUserSubChoice();

        switch (userChoice) {
            case 1:
                showVehicles();
                break;
            case 2:
                showVehiclesDes();
                break;
        }
    }
}
