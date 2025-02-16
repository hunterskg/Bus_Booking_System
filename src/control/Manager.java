package control;

import java.util.Scanner;
import linkedlist.BusLinkedList;
import object.Bus;

public class Manager {

    Scanner sc = new Scanner(System.in);
    Validation valid = new Validation();

    // Input a non-empty string
    public String inputString(String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            if (valid.checkString(input)) {
                return input;
            } else {
                System.err.println("Error: Input cannot be empty. Please try again.");
            }
        }
    }

    // Input an integer
    public int inputInt(String msg, String err, int min, int max) {
        while (true) {
            int number;
            System.out.print(msg);
            String input = sc.nextLine();
            if (valid.checkInt(input)) {
                number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                }
            } else {
                System.err.println(err);
            }
        }
    }

    // Input a double with range validation
    public double inputDouble(String msg, String err, double max, double min) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            if (valid.checkDouble(input)) {
                double number = Double.parseDouble(input);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.err.println(err);
                }
            } else {
                System.err.println("Error: Invalid format. Please enter a valid number.");
            }
        }
    }

    // Input a unique bus code
    public String inputBcode(String msg, BusLinkedList busList) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            if (valid.checkBcode(input, busList)) {
                return input;
            } else {
                System.err.println("Error: Code already exists or is invalid. Please try again.");
            }
        }
    }

    // Input a Bus object
    public Bus inputBus(BusLinkedList busList) {
        System.out.println("----- Enter Bus Information -----");
        String bcode = inputBcode("Enter bus code: ", busList);
        String bnum = inputString("Enter bus number: ");
        String dstation = inputString("Enter departing station: ");
        String astation = inputString("Enter arriving station: ");
        double dtime = inputDouble("Enter departing time (0-24): ", "Error: Time must be between 0 and 24.", 24, 0);
        double atime = inputDouble("Enter arriving time: ", "Error: Arrival time must be >= departing time and <= 24.", 24, dtime);

        int seat = inputInt("Enter total seats: ", "Error: Total seats must be greater than 0 and smaller than 96", 0, 96);

        int booked = inputInt("Enter booked seats: ", "Seat must be between 0 and 96", 0, seat);

        return new Bus(bcode, bnum, dstation, astation, dtime, seat, booked, atime);
    }
}
