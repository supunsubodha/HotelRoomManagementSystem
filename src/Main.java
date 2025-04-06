//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {

    //Loading bar
    static void loading_bar(String loading_type){
        System.out.print(loading_type + "[");
        for(int i=0; i<20; i++){
            System.out.print("=");
            int delay;
            if(i%3==0){
                delay=500;
            } else if (i%3==2) {
                delay=150;
            }else{
                delay = 400;
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("] Done!");
    }


    static String[] file_read_write(String op_type,String data) {
        op_type = op_type.toLowerCase();
        data = data.toUpperCase();

        if(op_type.equals("read")){
            loading_bar("Reading data...");
        }

        //Check operation type(read/write)
        String[] roomsArray = null;
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

        if (op_type.equals("write")) {

            loading_bar("Writing data...");

            if(data.contains("/")){//Remove old room code with old details and initialize new room code with updated details to data variable
                String[] cracked_data = data.split("/");
                String toremove = cracked_data[0];
                data = cracked_data[1];
                allrooms.remove(toremove);
            }

            if(!data.isEmpty()){//append values to arrays if necessary
                allrooms.add(data);
            }

            // Clears the file by writing an empty string
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("allrooms.txt"))) {
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Append data line by line
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("allrooms.txt", true))) {
                for(String element:allrooms){
                    writer.write(element);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    return allrooms.toArray(new String[allrooms.size()]);
    }


    static void show_all_rooms(){

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
        Scanner sc = new Scanner(System.in);
        loading_bar("Program is starting...");
        System.out.println("Welcome to Hotel Room Management System");
        //Main menu loop
        for (;;){
            //This loop will be continuously loop, until user input "11" to Exit

            System.out.println("Select an action:");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("1.Show all rooms");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("2.Show booked rooms");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("3.Show available rooms");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("4.Add a room");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("5.Remove a room");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("6.Book a room");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("7.Release a room");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("8.Show A/C rooms");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("9.Show non-A/C rooms");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("10.View rooms prices");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("11.Exit");
            System.out.println();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

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