/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import linkedlist.BusLinkedList;
import linkedlist.Node;
import object.Bus;

/**
 *
 * @author FPT SHOP
 */
public class main {
    public static void main(String[] args) {
        linkedlist.BusLinkedList buslist = new BusLinkedList();
        buslist.addLast(new Bus("B006", "101", "Station A", "Station B", 8.30, 10, 40, 10));
        buslist.addLast(new Bus("B002", "102", "Station C", "Station D", 9.00, 11, 50, 20));
        buslist.addLast(new Bus("B003", "103", "Station E", "Station F", 6.45, 8, 30, 15));
        buslist.addLast(new Bus("B004", "104", "Station G", "Station H", 12.15, 14, 45, 25));
        buslist.addLast(new Bus("B005", "105", "Station I", "Station J", 15.00, 17, 60, 30));
        buslist.traverse();
        System.out.println("");
        buslist.deleteByCode("B005");
        buslist.traverse();
        System.out.println("");
        buslist.sortByCode();
        buslist.traverse();
        System.out.println("");
        Node x = buslist.searchByCode("B003");
        System.out.println(x.info);
        
    }
}
