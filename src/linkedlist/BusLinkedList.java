/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlist;

import object.Bus;

/**
 *
 * @author FPT SHOP
 */
public class BusLinkedList {

    private Node head;
    private Node tail;

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

    public void traverse() {
        Node q = head;
        while (q != null) {
            System.out.println(q.info);
            q = q.next;
        }
    }

    public void addFirst(Bus bus) {
        Node newNode = new Node(bus);
        newNode.next = head;
        head = newNode;
    }

    public void addLast(Bus bus) {
        if (isEmpty()) {
            head = tail = new Node(bus); // Both head and tail point to the new node
        } else {
            Node newNode = new Node(bus); // `next` defaults to null
            tail.next = newNode;         // Point the current tail to the new node
            tail = newNode;              // Update the tail reference
        }
    }

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

}
