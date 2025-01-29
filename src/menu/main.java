/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import control.Manager;
import java.util.Date;
import linkedlist.BusLinkedList;
import linkedlist.Node;
import object.Booking;
import object.Bus;

/**
 *
 * @author FPT SHOP
 */
public class main {

    public static void main(String[] args) {
        Booking booking1 = new Booking("B001", "P001", new Date(), 0, 5);
        Booking booking2 = new Booking("B002", "P002", 1, 3); // Uses today's date

        // Display bookings
        System.out.println("\n--- Booking 1 ---");
        System.out.println(booking1);

        System.out.println("\n--- Booking 2 ---");
        System.out.println(booking2);

    }
}
