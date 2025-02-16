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

    private Node head;
    private Node tail;

    String filePath = "Buses.txt";
    
    
    public Node getHead() {
        return head;
    }

    public Node getTail() {
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
        Node p = head;
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
                String[] readedFileParts = readedFile.split(",");
                if (readedFileParts.length == 8) {
                    String bcode = readedFileParts[0].trim();
                    String bnum = readedFileParts[1].trim();
                    String dstation = readedFileParts[2].trim();
                    String astation = readedFileParts[3].trim();
                    double dtime = Double.parseDouble(readedFileParts[4].trim());
                    int seat = Integer.parseInt(readedFileParts[5].trim());
                    int booked = Integer.parseInt(readedFileParts[6].trim());
                    double atime = Double.parseDouble(readedFileParts[7].trim());
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
            head = tail = new Node(bus); // Both head and tail point to the new node
        } else {
            Node newNode = new Node(bus); // `next` defaults to null
            tail.next = newNode;         // Point the current tail to the new node
            tail = newNode;              // Update the tail reference
        }
    }

    //1.3 Display data
    public void traverse() {
        Node q = head;
        while (q != null) {
            System.out.println(q.info);
            q = q.next;
        }
    }

    //1.4 Save bus list to file
    public void saveBusesToFile() {
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

    //1.5 Search by bcode
    public Node searchByCode(String code) {
        Node p = head;
        while (p != null) {
            if (p.info.getBcode().equals(code)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    //1.6 Delete by bcode
    public void deleteByCode(String code) {
        Node p = searchByCode(code);
        Node q = head;
        if (p == head) {
            head = head.next;
        } else {
            while (q.next != p) {
                q = q.next;
            }
            q.next = p.next;
        }
    }

    //1.7 Sort by bcode
    public void sortByCode() {
        for (Node a = head; a != null; a = a.next) {
            for (Node b = a.next; b != null; b = b.next) {
                if (a.info.getBcode().compareTo(b.info.getBcode()) > 0) {
                    swap(a, b);
                }
            }
        }
    }

    void swap(Node a, Node b) {
        Bus temp = a.info;
        a.info = b.info;
        b.info = temp;
    }

    //1.8 Input & add to beginning
    public void addFirst(Bus bus) {
        Node newNode = new Node(bus);
        newNode.next = head;
        head = newNode;
    }

    //1.9 add after position k
    public void addAfterPositionK(Bus bus, int k) {
        Node newNode = new Node(bus);
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
        Node temp = head;
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
        Node temp = head;
        int index = 0;
        while (temp != null && index < k - 1) {
            temp = temp.next;
            index++;
        }
        if (temp == null || temp.next == null) {
            System.out.println("Out of list");
            return;
        }
        Node p = temp.next;
        temp.next = p.next;
        if (p == tail) {
            tail = temp;
        }
    }

    //1.11 Search by name
    public void searchByName(String name) {
        Node temp = head;
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

        Node busNode = busList.searchByCode(bcode);
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
