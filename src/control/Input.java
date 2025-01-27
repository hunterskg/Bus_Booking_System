/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.Scanner;
import linkedlist.BusLinkedList;
import object.Bus;

/**
 *
 * @author FPT SHOP
 */
public class Input {
    Scanner sc = new Scanner(System.in);
    Validation valid = new Validation();
    public String inputString(String msg){
        System.out.println(msg);
        String input = sc.nextLine();
        if(valid.checkString(input)){
            return input;
        }
        else{
            System.out.println("Wrong format");
            return inputString(msg);
        }
        
    }
    public int inputInt(String msg){
        System.out.println(msg);
        String input = sc.nextLine();
        if(valid.checkInt(input)){
            return Integer.valueOf(input);
        }
        else{
            System.out.println("Wrong format");
            return inputInt(msg);
        }
    }
    public double inputDouble(String msg){
        System.out.println(msg);
        String input = sc.nextLine();
        if (valid.checkDouble(input)){
            return Double.valueOf(input);
        }
        else {
            System.out.println("Wrong format");
            return inputDouble(msg);
        }
    }
    public String inputBcode(String msg){
        System.out.println(msg);
        String input = sc.nextLine();
        if (valid.checkBcode(input)){
            return input;
        }
        else {
            System.out.println("Code already exist");
            return inputBcode(msg);
        }
    }
    
    
    // INPUT BUS
    public Bus inputBus(BusLinkedList busList){
        System.out.println("-----Enter Bus Information");
        String bcode = inputBcode("Enter bus code: ");
        String bnum = inputString("Enter bus number: ");
        String dstation = inputString("Enter departing station: ");
        String astation = inputString("Enter arriving station: ");
        double dtime = inputDouble("Enter departing time (0-24): ");
        double atime;
        do {
            atime = inputDouble("Enter arriving time: ");
            if (atime < dtime || atime > 24) {
                System.err.println("dtime ≤atime≤ 24");
            }
        } while (atime < dtime || atime > 24);
        int seat = inputInt("Enter total seats: ");
        while (seat <= 0) {
            System.out.println("Seat must be > 0");
            seat = inputInt("Enter total seats: ");
        }
        int booked = inputInt("Enter booked seats: ");
        while (booked < 0 || booked > seat) {
            System.out.println("Booked seats must be between 0 and total seats.");
            booked = inputInt("Enter booked seats");
        }
        return new Bus(bcode, bnum, dstation, astation, dtime, seat, booked, atime);
    }
}
