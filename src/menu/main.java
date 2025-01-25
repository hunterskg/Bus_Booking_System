/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import linkedlist.BusLinkedList;
import object.Bus;

/**
 *
 * @author FPT SHOP
 */
public class main {
    public static void main(String[] args) {
        linkedlist.BusLinkedList buslist = new BusLinkedList();
  //      buslist.addFirst(new Bus("B001", "123", "Station A", "Station B", 8.30, 10, 40, 10));
        buslist.addLast(new Bus("B002", "124", "Station C", "Station D", 9.00, 11, 50, 20));
        buslist.traverse();
     //   buslist.deleteByCode("B001");
    //    buslist.traverse();
    }
}
