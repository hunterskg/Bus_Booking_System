/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlist;

import object.Passenger;

/**
 *
 * @author FPT SHOP
 */
public class PassengerLinkedList {
    private Node head;
    private Node tail;

    // Node class for the linked list
    public class Node {
        public Passenger info;
        public Node next;

        public Node(Passenger passenger) {
            this.info = passenger;
            this.next = null;
        }
    }
    
    public void addLast(Passenger passenger) {
        Node newNode = new Node(passenger);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }
    public void traverse() {
        Node q = head;
        while (q != null) {
            System.out.println(q.info);
            q = q.next;
        }
    }
    
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
