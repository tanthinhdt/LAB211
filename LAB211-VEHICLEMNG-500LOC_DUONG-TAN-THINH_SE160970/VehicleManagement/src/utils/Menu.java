
package utils;

import java.util.Scanner;
import java.util.Vector;

public class Menu extends Vector<String> {
    Scanner sc = new Scanner(System.in);
    public Menu() {
        super();
    }
    
    // Print main menu and get user's choice
    public int getUserChoice() {
        int i = 1;
        System.out.println("\n------------------------------");
        System.out.println("   MENU");
        for (String option : this) {
            System.out.println(i + ". " + option);
            i++;
        }
        return utils.Inputter.inputPosInt("Choose 1 option: ");
    }
    
    // Print submenu and get user's choice
    public int getUserSubChoice() {
        int i = 1;
        for (String option : this) {
            System.out.println("    " + i + ". " + option);
            i++;
        }
        return utils.Inputter.inputPosInt("    Choose 1 option: ");
    }
}
