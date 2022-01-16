package utils;

import java.util.Calendar;
import java.util.Scanner;

public class Inputter {

    public static boolean inputBoolean(String message) {
        Scanner sc = new Scanner(System.in);
        String ans;
        boolean flag = true;
        do {
            System.out.print(message);
            ans = sc.nextLine();
            if (ans.startsWith("Y") || ans.startsWith("y")) {
                flag = true;
            } else if (ans.startsWith("N") || ans.startsWith("n")) {
                flag = false;
            } else {
                System.out.println("Please enter Y or N");
                ans = null;
            }
        } while (ans == null);
        return flag;
    }

    // Input and return a string of boolean or empty string
    public static String inputBooleanString(String message) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        boolean flag;
        do {
            System.out.print(message);
            String temp = sc.nextLine();
            if (temp.isEmpty())
                break;
            if (temp.startsWith("Y") || temp.startsWith("y") || temp.startsWith("N") || temp.startsWith("n")) {
                flag = false;
            } else {
                System.out.println("Please enter Y or N");
                flag = true;
            }
        } while (flag);
        return ans;
    }

    // Check answer for boolean question
    public static boolean validateBoolean(String ans) throws Exception {
        if (ans.startsWith("Y") || ans.startsWith("y")) {
            return true;
        } else if (ans.startsWith("N") || ans.startsWith("n")) {
            return false;
        }
        throw new Exception("Please enter Y or N");
    }

    public static int inputPosInt(String message) {
        Scanner sc = new Scanner(System.in);
        int ans = 0;
        boolean flag;
        do {
            try {
                System.out.print(message);
                ans = Integer.parseInt(sc.nextLine());
                if (ans < 0) {
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                System.out.println("Please enter a positive integer");
                flag = true;
            }
        } while (flag);
        return ans;
    }
    
    // Check if a string is a positive integer
    public static int validatePosInt(String ans) throws Exception {
        int num = Integer.parseInt(ans);
        if (num > 0)
            return num;
        throw new Exception();
    }

    // Input and return a string of positive integer or empty string
    public static String inputPosIntString(String message) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        boolean flag;
        do {
            try {
                System.out.print(message);
                String temp = sc.nextLine();
                if (temp.isEmpty()) {
                    break;
                }
                if (Integer.parseInt(temp) <= 0) {
                    throw new Exception();
                }
                ans = temp;
                flag = false;
            } catch (Exception e) {
                System.out.println("Please enter a positive integer");
                flag = true;
            }
        } while (flag);
        return ans;
    }

    public static int inputYear(String message) {
        Scanner sc = new Scanner(System.in);
        int year = 0;
        int now = Calendar.getInstance().get(Calendar.YEAR);
        do {
            try {
                System.out.print(message);
                year = Integer.parseInt(sc.nextLine());
                if (year <= 0 || now < year) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid year");
            }
        } while (year <= 0 || now < year);
        return year;
    }
    
    // Check if a string is a valid year
    public static int validateYear(String ans) throws Exception {
        int year = Integer.parseInt(ans);
        int now = Calendar.getInstance().get(Calendar.YEAR);
        if (0 < year && year <= now)
            return year;
        throw new Exception();
    }

    // Input and return a valid year or empty string
    public static String inputYearString(String message) {
        Scanner sc = new Scanner(System.in);
        String year = "";
        int now = Calendar.getInstance().get(Calendar.YEAR);
        boolean flag;
        do {
            try {
                System.out.print(message);
                String temp = sc.nextLine();
                if (temp.isEmpty()) {
                    break;
                }
                if (Integer.parseInt(temp) <= 0 || now < Integer.parseInt(temp)) {
                    throw new Exception();
                }
                year = temp;
                flag = false;
            } catch (Exception e) {
                System.out.println("Please enter a valid year");
                flag = true;
            }
        } while (flag);
        return year;
    }

    public static String inputString(String message, String pattern) {
        Scanner sc = new Scanner(System.in);
        String ans;
        do {
            System.out.println(message);
            ans = sc.nextLine();
            if (ans.matches(pattern)) {
                System.out.println("Please enter a valid string");
            }
        } while (!ans.matches(pattern));
        return ans;
    }
}
