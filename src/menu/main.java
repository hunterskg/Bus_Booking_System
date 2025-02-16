package menu;

import control.Manager;
import control.*;
import java.text.ParseException;
import linkedlist.*;
import object.*;

public class main {

    public static void main(String[] args) throws ParseException {

        //Link list
        BookingLinkedList bookingList = new BookingLinkedList();
        BusLinkedList busList = new BusLinkedList();
        PassengerLinkedList passList = new PassengerLinkedList();

        //input and display handler
        Manager manage = new Manager();
        UI ui = new UI();

        //Load file
        busList.loadBusesFromFile();
        passList.loadPassengersFromFile();
        bookingList.loadBookingFromFile();

        while (true) {
            int menuChoice;
            ui.showMenu();
            menuChoice = ui.getChoiceMenu();
            switch (menuChoice) {
                case 1:
                    int busChoice;
                    do {
                        ui.showBusMenu();
                        busChoice = ui.getChoiceBusMenu();
                        switch (busChoice) {
                            case 1:
                                Bus busAddLast = manage.inputBus(busList);
                                busList.addLast(busAddLast);
                                busList.saveBusesToFile();
                                break;
                            case 2:
                                busList.traverse();
                                break;
                            case 3:
                                String bcodeSearch = manage.inputBcode("Please enter bcode to search: ", busList);
                                busList.searchByCode(bcodeSearch);
                                break;
                            case 4:
                                String bcodeDelete = manage.inputString("Please enter bcode to delete: ");
                                busList.deleteByCode(bcodeDelete);
                                busList.saveBusesToFile();
                                break;
                            case 5:
                                busList.sortByCode();
                                busList.saveBusesToFile();
                                break;
                            case 6:
                                Bus bus = manage.inputBus(busList);
                                busList.addFirst(bus);
                                busList.saveBusesToFile();
                                break;
                            case 7:
                                Bus busAfterPosition = manage.inputBus(busList);
                                int bPosition = manage.inputInt("Please enter position to add Bus: ", "Position not available", 0, busList.size());
                                busList.addAfterPositionK(busAfterPosition, bPosition);
                                busList.saveBusesToFile();
                                break;
                            case 8:
                                int bDeletePosition = manage.inputInt("Please enter position to delete Bus: ", "Position not available", 0, busList.size());
                                busList.deletePositionK(bDeletePosition);
                                busList.saveBusesToFile();
                                break;
                            case 9:
                                String bNameToSearch = manage.inputString("Please enter bus name to search: ");
                                busList.searchByName(bNameToSearch);
                                break;
                            case 10:
                                // Chưa viết method
                                break;
                        }
                    } while (busChoice != 0); // Return to main menu when user selects 0
                    break; // Exit case 1 and go back to main menu

                case 2:
                    int passengersChoice;
                    do {
                        ui.showPassengerMenu();
                        passengersChoice = ui.getChoicePassengersMenu();
                        switch (passengersChoice) {
                            case 1:
                                // Chưa có input passenger
                                break;
                            case 2:
                                passList.traverse();
                                passList.savePassengersToFile();
                                break;
                            case 3:
                                String pcodeToSearch = manage.inputString("Please enter passenger code to search: ");
                                passList.searchByPcode(pcodeToSearch);
                                break;
                            case 4:
                                String pcodeToDelete = manage.inputString("Please enter passenger code to delete: ");
                                passList.deleteByPcode(pcodeToDelete);
                                break;
                            case 5:
                                String pnameToSearch = manage.inputString("Please enter passenger name to search: ");
                                passList.searchByName(pnameToSearch);
                                break;
                            case 6:
                                // Chưa có method
                                break;
                        }
                    } while (passengersChoice != 0);
                    break;

                case 3:
                    int bookingChoice;
                    do {
                        ui.showBookingMenu();
                        bookingChoice = ui.getChoiceBookingMenu();
                        switch (bookingChoice) {
                            case 1:
                                // Chưa có method
                                break;
                            case 2:
                                bookingList.traverse();
                                break;
                            case 3:
                                bookingList.sortByBcodeAndPcode();
                                bookingList.saveBookingToFile();
                                break;
                            case 4:
                                String bcodeForBooking = manage.inputString("Please enter bus code for booking: ");
                                String pcodeForBooking = manage.inputString("Please enter passenger code for booking: ");
                                bookingList.payBooking(bcodeForBooking, pcodeForBooking);
                                bookingList.saveBookingToFile();
                                break;
                        }
                    } while (bookingChoice != 0);
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    System.exit(0);
            }
        }
    }
}
