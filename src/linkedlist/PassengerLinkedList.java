/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlist;

import java.io.*;
import object.Passenger;

/**
 *
 * @author FPT SHOP
 */
public class PassengerLinkedList {

    private Node head;
    private Node tail;

    String filePath = "Passengers.txt";

    // Node class for the linked list
    public class Node {

        public Passenger info;
        public Node next;

        public Node(Passenger passenger) {
            this.info = passenger;
            this.next = null;
        }
    }

    //2.1 Load data from file
    public void loadPassengersFromFile() {
        try {
            BufferedReader bReader = new BufferedReader(
                    new FileReader(filePath));
            String readedFile;
            while ((readedFile = bReader.readLine()) != null) {
                String[] readedFileParts = readedFile.split(",");
                if (readedFileParts.length == 3) {
                    String pcode = readedFileParts[0].trim();
                    String name = readedFileParts[1].trim();
                    String phone = readedFileParts[2].trim();
                    addLast(new Passenger(pcode, name, phone));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Print list
        traverse();
    }

    //2.2 Input & add to the end    
    public void addLast(Passenger passenger) {
        Node newNode = new Node(passenger);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    //2.3 Display data
    public void traverse() {
        Node q = head;
        while (q != null) {
            System.out.println(q.info);
            q = q.next;
        }
    }

    //2.4 Save passengers list to file
    public void savePassengersToFile() {
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
    
    //2.5 Search by pcode
    public Node searchByPcode(String pcode) {
        Node p = head;
        while (p != null) {
            if (p.info.getPcode().equals(pcode)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    //2.6 Delete by pcode
    public void deleteByPcode(String pcode) {
        Node p = searchByPcode(pcode);
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

    
    //2.7 Search by name
    public void searchByName(String name) {
        Node temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.info.getName().equalsIgnoreCase(name)) {
                System.out.println(temp.info);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Not found");
        }
    }

}
