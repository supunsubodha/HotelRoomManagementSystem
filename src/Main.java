//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static void show_all_rooms(){
        //A list of all rooms in the hotel should be output.
    }

    static void show_booked_rooms(){
        //A list of all booked rooms in the hotel should be output.
    }
    static void show_available_rooms(){
        //A list of all available rooms in the hotel should be output.
    }
    static void add_a_room(){
        //Allows user to add new room to the system.
    }

    static void remove_a_room(){
        //Allows user to remove a room from the system.
    }
    static void book_a_room(){
       // Allows user to book a room.
    }
    static void release_a_room(){
        //Allows user to release room.(When the customer cancels the reservation)
    }
    static void show_ac_rooms(){
        //A list of all ac rooms in the hotel should be output.
    }
    static void show_nonac_rooms(){
        //A list of all non-ac rooms in the hotel should be output.
    }
    static void view_rooms_prices(){
        //Allows user to see prices of the rooms.
        // AC rooms have a fixed price of x, and non-AC rooms have a fixed price of y.
    }


    public static void main(String[] args) {
        // Create an ArrayList to hold room numbers
        ArrayList<String> allrooms = new ArrayList<>();
        String filepath = "allrooms.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                // Add each line to the ArrayList
                allrooms.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Hotel Room Management System");
        //Main menu loop
        for (;;){
            //This loop will be continuously loop, until user input "11" to Exit

            System.out.println("Select an action:");
            System.out.println("1.Show all rooms");
            System.out.println("2.Show booked rooms");
            System.out.println("3.Show available rooms");
            System.out.println("4.Add a room");
            System.out.println("5.Remove a room");
            System.out.println("6.Book a room");
            System.out.println("7.Release a room");
            System.out.println("8.Show A/C rooms");
            System.out.println("9.Show non-A/C rooms");
            System.out.println("10.View rooms prices");
            System.out.println("11.Exit");

            //Allows the user to select an action
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();

            if (choice == 1) {
                show_all_rooms();
            } else if (choice == 2) {
                show_booked_rooms();
            } else if (choice == 3) {
                show_available_rooms();
            } else if (choice == 4) {
                add_a_room();
            } else if (choice == 5) {
                remove_a_room();
            } else if (choice == 6) {
                book_a_room();
            } else if (choice == 7) {
                release_a_room();
            } else if (choice == 8) {
                show_ac_rooms();
            } else if (choice == 9) {
                show_nonac_rooms();
            } else if (choice == 10) {
                view_rooms_prices();
            } else if (choice == 11) {
                break;
            }else{
                System.out.println("Invalid choice");
            }

        }
    }
}