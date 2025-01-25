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

    Node head, tail;

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
        while (q.next != p) {
            q = q.next;
        }
        q.next = p.next;
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

}
