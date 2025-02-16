package control;

import java.util.Date;
import java.util.Scanner;
import linkedlist.*;
import object.*;

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
                System.err.println("Code already exists or is invalid. Please try again.");
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

    // Input passenger
    public Passenger inputPassenger(PassengerLinkedList passengerList) {
        System.out.println("\n----- Enter Passenger Information -----");

        // Ensure unique passenger code
        String pcode = inputString("Enter passenger code: ");
        while (passengerList.searchByPcode(pcode) != null) {
            System.err.println("Error: Passenger code already exists. Please enter a unique code.");
            pcode = inputString("Enter passenger code: "); // Ask for input again
        }

        String name = inputString("Enter passenger name: ");

        // Ensure unique and valid phone number
        String phone = inputString("Enter passenger phone number (must start with '0' and be 11 digits): ");
        while (!phone.matches("0\\d{10}") || passengerList.searchByPhone(phone)) {
            if (!phone.matches("0\\d{10}")) {
                System.err.println("Error: Phone number must start with '0' and contain exactly 11 digits.");
            } else {
                System.err.println("Error: Phone number already exists. Please enter a unique phone number.");
            }
            phone = inputString("Enter passenger phone number (must start with '0' and be 11 digits): ");
        }

        return new Passenger(pcode, name, phone);
    }

    //input booking 
    public Booking inputBooking(BookingLinkedList bookingList, BusLinkedList busList, PassengerLinkedList passengerList) {
        System.out.println("\n----- Enter Booking Information -----");

        // Get valid bus code
        String bcode = inputString("Enter bus code: ");
        BusNode busToSearch = busList.searchByCode(bcode);
        while (busToSearch == null) {
            System.err.println("Error: Bus with code " + bcode + " not found.");
            bcode = inputString("Enter a valid bus code: ");
            busToSearch = busList.searchByCode(bcode);

        }

        // Get valid passenger code
        String pcode = inputString("Enter passenger code: ");
        PassengerLinkedList.Node passenger = passengerList.searchByPcode(pcode);
        while (passenger == null) {
            System.err.println("Error: Passenger with code " + pcode + " not found.");
            pcode = inputString("Enter a valid passenger code: ");
            passenger = passengerList.searchByPcode(pcode);
        }

        // Ensure booking seats do not exceed available seats
        Bus bus = new Bus();
        int availableSeats = bus.getSeat() - bus.getBooked();
        if (availableSeats == 0) {
            System.err.println("Error: No available seats on this bus.");
            return null;
        }

        int bookedSeats = inputInt("Enter number of seats to book (Max " + availableSeats + "): ",
                "Error: Seats must be between 1 and " + availableSeats, 1, availableSeats);

        // Set booking details
        Date odate = new Date(); // Booking date set to today
        int paid = 0; // Default value, unpaid

        // Update bus availability
        bus.setBooked(bus.getBooked() + bookedSeats);

        return new Booking(bcode, pcode, odate, paid, bookedSeats);
    }

}
