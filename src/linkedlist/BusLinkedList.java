/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlist;

import java.io.*;
import object.Bus;

/**
 *
 * @author FPT SHOP
 */
public class BusLinkedList {

    private BusNode head;
    private BusNode tail;

    String filePath = "Buses.txt";

    public BusNode getHead() {
        return head;
    }

    public BusNode getTail() {
        return tail;
    }

    public BusLinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = tail = null;
    }

    public int size() {
        BusNode p = head;
        int a = 0;
        while (p != null) {
            p = p.next;
            a++;
        }
        return a;
    }

    //1.1 Load Buslist from file
    public void loadBusesFromFile() {
        File file = new File(filePath); // Create a File object for the path

        if (!file.exists()) { // Check if the file exists
            System.out.println("File not found: " + filePath);
            return;
        }

        try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
            String readedFile;
            while ((readedFile = bReader.readLine()) != null) {

                // Split only at ", " but not inside numbers
                String[] parts = readedFile.split(";");

                if (parts.length == 8) {
                    String bcode = parts[0].split(": ")[1].trim();
                    String bnum = parts[1].split(": ")[1].trim();
                    String dstation = parts[2].split(": ")[1].trim();
                    String astation = parts[3].split(": ")[1].trim();

                    // Replace commas in numbers with dots before parsing
                    double dtime = Double.parseDouble(parts[4].split(": ")[1].trim().replace(",", "."));
                    int seat = Integer.parseInt(parts[5].split(": ")[1].trim());
                    int booked = Integer.parseInt(parts[6].split(": ")[1].trim());
                    double atime = Double.parseDouble(parts[7].split(": ")[1].trim().replace(",", "."));

                    addLast(new Bus(bcode, bnum, dstation, astation, dtime, seat, booked, atime));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid data format in file: " + e.getMessage());
        }
    }

    //1.2 Input and add to the end
    public void addLast(Bus bus) {
        if (isEmpty()) {
            head = tail = new BusNode(bus); // Both head and tail point to the new node
        } else {
            BusNode newNode = new BusNode(bus); // `next` defaults to null
            tail.next = newNode;         // Point the current tail to the new node
            tail = newNode;              // Update the tail reference
        }
    }

    //1.3 Display data
    public void traverse() {
        BusNode q = head;
        while (q != null) {
            System.out.println(q.info);
            q = q.next;
        }
        System.out.println();
    }

    //1.4 Save bus list to file
    public void saveBusesToFile() {
        try (BufferedWriter bwriter = new BufferedWriter(new FileWriter(filePath))) {
            BusNode temp = head;
            while (temp != null) {
                bwriter.write(temp.info.toString());  // Write the booking data
                bwriter.newLine();  // Move to the next line
                temp = temp.next;  // Move to the next node
            }
        } catch (Exception e) {
        }
    }

    //1.5 Search by bcode
    public BusNode searchByCode(String code) {
        BusNode p = head;
        while (p != null) {
            if (p.info.getBcode().equals(code)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }
    
    public void searchByBcodeResult(String pcode) {
        BusNode p = searchByCode(pcode);
        if (searchByCode(pcode) != null) {
            System.out.println(p.info);
        } else {
            System.out.println("Bus not found");
        }
    }

    //1.6 Delete by bcode
    public void deleteByCode(String code, BookingLinkedList bookingList) {
        if (head == null) {
            System.out.println("The bus list is empty.");
            return;
        }

        //Step 1: Delete all bookings for this bus first
        BookingLinkedList.Node bookingPrev = null;
        BookingLinkedList.Node bookingCurrent = bookingList.getHead();

        while (bookingCurrent != null) {
            if (bookingCurrent.info.getBcode().equalsIgnoreCase(code)) {
                if (bookingPrev == null) {
                    bookingList.setHead(bookingCurrent.next); // Remove head
                } else {
                    bookingPrev.next = bookingCurrent.next; // Remove current node
                }
            } else {
                bookingPrev = bookingCurrent; // Move prev pointer forward
            }
            bookingCurrent = bookingCurrent.next;
        }

        // Step 2: Delete the bus after related bookings are removed
        BusNode prev = null;
        BusNode current = head;

        while (current != null && !current.info.getBcode().equalsIgnoreCase(code)) {
            prev = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Bus with code " + code + " not found.");
            return;
        }

        if (prev == null) {
            head = current.next; // Delete head
        } else {
            prev.next = current.next; // Delete middle or tail node
        }

        if (current == tail) {
            tail = prev; // Update tail if last node was deleted
        }

        System.out.println("Bus with code " + code + " and its bookings have been deleted.");
    }

    //1.7 Sort by bcode
    public void sortByCode() {
        for (BusNode a = head; a != null; a = a.next) {
            for (BusNode b = a.next; b != null; b = b.next) {
                if (a.info.getBcode().compareTo(b.info.getBcode()) > 0) {
                    swap(a, b);
                }
            }
        }
        System.out.println("Sort successfully");
    }

    void swap(BusNode a, BusNode b) {
        Bus temp = a.info;
        a.info = b.info;
        b.info = temp;
    }

    //1.8 Input & add to beginning
    public void addFirst(Bus bus) {
        BusNode newNode = new BusNode(bus);
        newNode.next = head;
        head = newNode;
    }

    //1.9 add after position k
    public void addAfterPositionK(Bus bus, int k) {
        BusNode newNode = new BusNode(bus);
        if (k < 0) {
            System.err.println("Position must be >= 0");
            return;
        }
        if (k == 0) {
            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head.next;
                head.next = newNode;
            }
            return;
        }
        BusNode temp = head;
        int index = 0;
        while (temp != null && index < k) {
            temp = temp.next;
            index++;
        }
        if (temp == null) {
            System.out.println("Out of list");
            return;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        if (temp == tail) {
            tail = newNode;
        }
    }

    //1.10 delete after position k
    public void deletePositionK(int k) {
        if (k < 0) {
            System.out.println("Position must be >= 0");
            return;
        }
        if (head == null) {
            System.out.println("The list is empty");
            return;
        }
        if (k == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return;
        }
        BusNode temp = head;
        int index = 0;
        while (temp != null && index < k - 1) {
            temp = temp.next;
            index++;
        }
        if (temp == null || temp.next == null) {
            System.out.println("Out of list");
            return;
        }
        BusNode p = temp.next;
        temp.next = p.next;
        if (p == tail) {
            tail = temp;
        }
    }

    //1.11 Search by name
    public void searchByName(String name) {
        BusNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.info.getBnum().equalsIgnoreCase(name)) {
                System.out.println(temp.info);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Not found");
        }
    }

    //1.12 Search bookings by bus code
    public void searchBookedByBcode(String bcode, BookingLinkedList bookingList, BusLinkedList busList, PassengerLinkedList passengerList) {

        BusNode busNode = busList.searchByCode(bcode);
        if (busNode == null) {
            System.err.println("Bus with code " + bcode + " not found.");
            return;
        }

        // Hiển thị thông tin xe buýt
        System.out.println("\n===== Bus Details =====");
        System.out.println(busNode.info);

        System.out.println("\n===== Passengers Who Booked This Bus =====");
        boolean foundPassenger = false;
        BookingLinkedList.Node bookingNode = bookingList.getHead();
        while (bookingNode != null) {
            if (bookingNode.info.getBcode().equalsIgnoreCase(bcode)) {

                PassengerLinkedList.Node passengerNode = passengerList.searchByPcode(bookingNode.info.getPcode());
                if (passengerNode != null) {
                    System.out.println(passengerNode.info);
                    foundPassenger = true;
                }
            }
            bookingNode = bookingNode.next;
        }

        if (!foundPassenger) {
            System.out.println("No passengers have booked this bus.");
        }
    }

}
