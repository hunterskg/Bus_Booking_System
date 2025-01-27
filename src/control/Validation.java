/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import linkedlist.BusLinkedList;
import linkedlist.Node;
import object.Bus;

/**
 *
 * @author FPT SHOP
 */
public class Validation {

   

    public boolean checkString(String input) {
        return !input.isEmpty();
    }

    public boolean checkDouble(String input) {

        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean checkInt(String input) {

        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean checkBcode(String input, BusLinkedList busList) {
        if (!checkString(input)) {
            System.out.println("Code can not be empty");
            return false;
        }
        Node temp = busList.getHead();
        while (temp != null) {
            if (temp.info.getBcode().equals(input)) {
                return false;
            }
            temp = temp.next;
        }

        return true;
    }

}
