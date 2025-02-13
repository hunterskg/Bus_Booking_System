/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.Scanner;
import linkedlist.*;

/**
 *
 * @author admin
 */
public class UI {

    Manager manage = new Manager();

    public void showMenu() {
        System.out.println("Bus Booking System");
        System.out.println("1. Manage Buses");
        System.out.println("2. Manage Passengers");
        System.out.println("3. Manage Bookings");
        System.out.println("4. Exit");
    }

    public int getChoiceMenu() {
        return manage.inputInt("Enter your choice: ", "Please input number betwwen 1 and 4", 1, 4);
    }
    
    public int getChoiceBusMenu() {
        return manage.inputInt("Enter your choice: ", "Please input number betwwen 0 and 10", 0, 10);
    }
    
    public int getChoicePassengersMenu() {
        return manage.inputInt("Enter your choice: ", "Please input number betwwen 0 and 6", 0, 6);
    }

    public int getChoiceBookingMenu() {
        return manage.inputInt("Enter your choice: ", "Please input number betwwen 0 and 4", 0, 4);
    }
    
   public void showBusMenu() {
        System.out.println("Bus Management");
        System.out.println("1. Add new bus");
        System.out.println("2. Display all buses");
        System.out.println("3. Search bus by code");
        System.out.println("4. Delete bus by code");
        System.out.println("5. Sort buses by code");
        System.out.println("6. Add bus at beginning");
        System.out.println("7. Add bus after position");
        System.out.println("8. Delete bus at position");
        System.out.println("9. Search bus by name");
        System.out.println("10. Search booked by bus code");
        System.out.println("0. Back to main menu");
    }

    public void showPassengerMenu() {
        System.out.println("Passenger Management");
        System.out.println("1. Add new passenger");
        System.out.println("2. Display all passengers");
        System.out.println("3. Search passenger by code");
        System.out.println("4. Delete passenger by code");
        System.out.println("5. Search passenger by name");
        System.out.println("6. Search buses by passenger code");
        System.out.println("0. Back to main menu");
    }

    public void showBookingMenu() {
        System.out.println("Booking Management");
        System.out.println("1. Book a bus");
        System.out.println("2. Display all bookings");
        System.out.println("3. Sort bookings by bus code and passenger code");
        System.out.println("4. Pay for booking");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");
    }

}
