/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlist;

import java.io.*;
import java.text.*;
import java.util.Date;
import object.Booking;
import object.Bus;
import object.Passenger;

/**
 *
 * @author FPT SHOP
 */
public class BookingLinkedList {

    Bus bus = new Bus();
    Passenger passenger = new Passenger();
    String filePath = "Booking.txt";
    Booking booking = new Booking();

    private Node head;
    private Node tail;

    // Node class for the linked list
    public class Node {

        public Booking info;
        public Node next;

        public Node(Booking booking) {
            this.info = booking;
            this.next = null;
        }
    }

    //3.1 Load data from file
    public void loadBookingFromFile() {
        try {
            BufferedReader bReader = new BufferedReader(
                    new FileReader(filePath));
            String readedFile;
            while ((readedFile = bReader.readLine()) != null) {
                String[] readedFileParts = readedFile.split(",");
                if (readedFileParts.length == 5) {
                    String bcode = readedFileParts[0].trim();
                    String pcode = readedFileParts[1].trim();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date odate = dateFormat.parse(readedFileParts[2].trim());
                    int paid = Integer.parseInt(readedFileParts[3].trim());
                    int seat = Integer.parseInt(readedFileParts[4].trim());
                    addLast(new Booking(bcode, pcode, odate, paid, seat));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Print list
        traverse();
    }
    //3.2 add booking to the end of the booking list

    public void addLast(Booking booking) {
        Node newNode = new Node(booking);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    //3.3 display
    public void traverse() {
        Node q = head;
        while (q != null) {
            System.out.println(q.info);
            q = q.next;
        }
    }

    public void bookBus(String bcode, String pcode, int seats, BusLinkedList busList, PassengerLinkedList passengerList) {
        // check if bus and passenger exists
        linkedlist.Node foundBus = busList.searchByCode(bcode);
        if (foundBus == null) {
            System.err.println("Bus does not exist");
        }
        PassengerLinkedList.Node foundPassenger = passengerList.searchByPcode(pcode);
        if (foundPassenger == null) {
            System.err.println("Passenger not found");
        }
        // check if booking seat is less than or equals to seat of found bus
        if (seats > (bus.getSeat() - bus.getBooked())) {
            System.err.println("Out of seats");
        }

        // odate to today, and paid to 0 substract booking seat from bus seat;addbooking seat tobus booked
        Booking booking = new Booking(bcode, pcode, 0, seats);
        bus.setBooked(bus.getBooked() + seats);
        bus.setSeat(bus.getSeat() - seats);
        addLast(booking);
        System.out.println("Booking success");
    }

    //3.3 Save booking list to file
    public void saveBookingToFile() {
        try (BufferedWriter bwriter = new BufferedWriter(new FileWriter(filePath))) {
            Node temp = head;
            while (temp != null) {
                bwriter.write(temp.info.toString());  // Write the booking data
                bwriter.newLine();  // Move to the next line
                temp = temp.next;  // Move to the next node
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.5 Sortby bcode + pcode
    public boolean shouldSwap(Node a, Node b) {
        return a.info.getBcode().compareToIgnoreCase(b.info.getBcode()) > 0 || (a.info.getBcode().equalsIgnoreCase(b.info.getBcode()) && a.info.getPcode().compareToIgnoreCase(b.info.getPcode()) > 0);
    }

    private void swap(Node a, Node b) {
        Booking temp = a.info;
        a.info = b.info;
        b.info = temp;
    }

    public void sortByBcodeAndPcode() {

        for (Node i = head; i != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                if (shouldSwap(i, j)) {
                    swap(i, j);
                }
            }
        }
    }

    //3.6 Pay booking by bcode + pcode
    public void payBooking(String bcode, String pcode) {
        Node temp = head;
        while (temp != null) {
            if (temp.info.getBcode().equalsIgnoreCase(bcode) || temp.info.getPcode().equalsIgnoreCase(pcode)) {
                if (temp.info.getPaid() == 0) {
                    temp.info.setPaid(1);
                    System.out.println("Your seat have been paid");
                } else {
                    System.out.println("Your seat have alreay paid");
                }
            }
            temp = temp.next;
        }
    }
}
