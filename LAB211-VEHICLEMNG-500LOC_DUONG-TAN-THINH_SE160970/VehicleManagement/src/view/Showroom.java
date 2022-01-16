package view;

import java.util.Scanner;
import utils.Menu;
import model.VehicleList;
import utils.Inputter;

public class Showroom {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VehicleList list = new VehicleList();
        Menu menu = new Menu();
        final String filename = "vehicles.txt";
        boolean isChanged = false;
        int userChoice;
        menu.add("Load data from file");
        menu.add("Add new vehicle");
        menu.add("Update vehicle");
        menu.add("Delete vehicle");
        menu.add("Search vehicle");
        menu.add("Show vehicle list");
        menu.add("Store data to file");
        menu.add("Quit");

        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case 1:
                    list.loadFromFile(filename);
                    isChanged = true;       // Need to save due to false data in original file
                    break;
                case 2:
                    list.addNewVehicle();
                    isChanged = true;
                    break;
                case 3:
                    list.updateVehicle();
                    isChanged = true;
                    break;
                case 4:
                    list.deleteVehicle();
                    isChanged = true;
                    break;
                case 5:
                    list.searchVehicle();
                    break;
                case 6:
                    list.showAllVehicles();
                    break;
                case 7:
                    list.saveToFile(filename);
                    break;
                default:
                    // Save any changes before exit
                    if (isChanged) {
                        if (Inputter.inputBoolean("Do you want to save any changes (Y/N)? ")) {
                            list.saveToFile(filename);
                        }
                    }
                    System.out.println("BYE BYE!!!");
            }
        } while (0 < userChoice && userChoice < 8);
    }
}
