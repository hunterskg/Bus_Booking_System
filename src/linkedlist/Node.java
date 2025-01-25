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
public class Node {

    Bus info;
    Node next;

    Node() {
    }

     public Node(Bus bus) {
        this.info = bus;
        this.next = null; // Default to null for new nodes
    }

    public Node(Bus bus, Node next) {
        this.info = bus;
        this.next = next;
    }
}
