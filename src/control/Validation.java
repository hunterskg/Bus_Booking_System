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
    BusLinkedList buslist = new BusLinkedList();
    public boolean checkString(String input) {
        return !input.isEmpty();
    }

    public boolean checkDouble(String input) {
        while (true) {
            try {
                Double.parseDouble(input);
                return true;
            } catch (Exception e) {
                return false;
            }

        }
    }

    public boolean checkInt(String input) {
        while (true) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
    public boolean checkBcode(String input){
         if (!checkString(input)) {
            System.out.println("Code can not be empty");
            return false;
        }
            Node temp = buslist.getHead();
            while (temp != null){
                if (temp.info.getBcode().equals(input)){
                    return false;
                }
                temp = temp.next;
            }
        
        return true;
    }
    
}
