//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

//...................................................................................................

public class Main {

    //Loading bar
    static void loading_bar(String loading_type){
        System.out.print(loading_type +"⌛"+ "[");
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

//.....................................................................................................................
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

//.........................................................................................................................
    static void show_all_rooms(){
        //A list of all rooms in the hotel should be output.
        String allrooms[]= file_read_write("read","null");
        for(String element:allrooms){
            System.out.println(element);
            System.out.println();
        }
        System.out.print("Press Enter to continue to the Main Menu!");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); //Hold user until user press Enter
    }

//.........................................................................................................................
    static void show_booked_rooms(){
        //A list of all booked rooms in the hotel should be output.
        String allrooms[]= file_read_write("read","null");
        for(String element:allrooms){
            String rdetails[] = element.split(",");
            String roomstatus;
            try{
                roomstatus = rdetails[2];
            }catch (ArrayIndexOutOfBoundsException e){
                continue;
            }
            if(roomstatus.equalsIgnoreCase("Booked")){
                System.out.println(element);
                System.out.println();
            }

        }
        System.out.print("Press Enter to go back to the Main Menu!:");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); //Hold user until user press Enter
    }

//...................................................................................................................
    static void show_available_rooms(){
        //A list of all available rooms in the hotel should be output.
        String allrooms[]= file_read_write("read","null");
        for(String element:allrooms){
            String rdetails[] = element.split(",");
            String roomstatus = rdetails[2];
            if(roomstatus.equalsIgnoreCase("Available")){
                System.out.println(element);
                System.out.println();
            }

        }
        System.out.print("Press Enter to go back to the Main Menu!/Continue process:");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); //Hold user until user press Enter
    }

//....................................................................................................................
    static void add_a_room(){
        //Allows user to add new room to the system.

        Scanner scanner = new Scanner(System.in);

        System.out.println("");

        String roomnumber;
        String roomtype;

        for(;;){
            System.out.println("Room number must include three (3) digits!");
            System.out.print("Enter room number: ");
            roomnumber = scanner.nextLine();
            if(roomnumber.length() == 3){
                break;
            }
            System.out.println("Please try again!");
        }

        System.out.println("Valid room types:AC,NON-AC");

        for(;;){
            System.out.print("Enter room type: ");
            roomtype = scanner.nextLine().toUpperCase();//Handled error by turning all inputs uppercase

            if(roomtype.equals("AC") || roomtype.equals("NON-AC")){
                break;
            }

            System.out.println("Invalid input!");
            System.out.println("Please try again!");
        }

        String roomcode = roomnumber+","+roomtype+","+"AVAILABLE"+","+"00"+","+"00"+","+"00";

        file_read_write("write",roomcode);

        System.out.print("Press Enter to go back to the Main Menu!:");
        scanner.nextLine(); //Hold user until user press Enter
    }

//.................................................................................................................
    static void remove_a_room(){
        //Allows user to remove a room from the system.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter room number/roomcode to remove:");
        String roomnumber = scanner.nextLine();
        String allrooms [] =file_read_write("read","null");
        for(String element:allrooms){
            if(element.contains(roomnumber)){
                String rtremove = element+"/ ";
                file_read_write("write",rtremove);
                break;
            }
        }
    }

//...................................................................................................................
    static void book_a_room(){
       // Allows user to book a room.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to check available rooms before book a room? (Y/N) :");
        String check = scanner.nextLine();
        if(check.equalsIgnoreCase("Y")){
            show_available_rooms();
        }
        System.out.println("Enter room number/roomcode of the room hopes to book:");
        String roomnumber = scanner.nextLine();
        String allrooms [] =file_read_write("read","null");
        for(String element:allrooms){
            if(element.contains(roomnumber)){
                String spelment[]=element.split(",");
                String codetosend=element+"/"+spelment[0]+","+spelment[1]+","+"Booked"+","+spelment[3]+","+spelment[4]+","+spelment[5];
                file_read_write("write",codetosend);
                break;
            }
        }
    }

//....................................................................................................................
    static void release_a_room(){
        //Allows user to release room.(When the customer cancels the reservation)
        
    }

//....................................................................................................................
    static void show_ac_rooms(){
        //A list of all ac rooms in the hotel should be output.
        String allrooms[] = file_read_write("read", "null");
        for (String element : allrooms) {
            String rdetails[] = element.split(",");
            String roomtype;
            try {
                roomtype = rdetails[1].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
            if (roomtype.equalsIgnoreCase("AC")) {
                System.out.println(element);
                System.out.println();
            }
        }

        System.out.print("Press Enter to go back to the Main Menu!: ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Hold user until user presses Enter

    }

//....................................................................................................................
    static void show_nonac_rooms(){
        //A list of all non-ac rooms in the hotel should be output.
        String[] allRooms = file_read_write("read", "null");

        System.out.println("List of NON-AC Rooms:");
        boolean found = false;


        for (String room : allRooms) {
            room=room.toUpperCase();
            if (room.contains("NON-AC")) {
                System.out.println(room);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No NON-AC rooms found.");
        }
    }

//...................................................................................................................
    static void view_room_prices(){
        //Allows user to see prices of the rooms.
        // AC rooms have a fixed price of x, and non-AC rooms have a fixed price of y.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter room number/room code:");
        String roomnumber = scanner.nextLine();
        String allrooms [] =file_read_write("read","null");
        for(String element:allrooms){
            if(element.contains(roomnumber)){
                String arr[]=element.split(",");
                String roomtype = arr[1];
                System.out.println();
                if(roomtype.equals("AC")){
                    System.out.println("Room no: "+arr[0]);
                    System.out.println("Room type: AC");
                    System.out.println("Hourly rate: LKR.2500.00");
                    System.out.println("Day rate: LKR.14000.00");
                }
                else if(roomtype.equals("NON-AC")){
                    System.out.println("Room no:"+arr[0]);
                    System.out.println("Room type: Non-AC");
                    System.out.println("Hourly rate: LKR.2000.00");
                    System.out.println("Day rate: LKR.10000.00");
                }
                System.out.print("Press Enter to go back to the Main Menu!:");
                scanner.nextLine(); //Hold user until user press Enter
            }
        }
    }

//..................................................................................................................
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loading_bar("Program is starting...");
        System.out.println();
        System.out.println("🤖 Welcome to Hotel Room Management System");
        System.out.println();
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
            System.out.println("10.View room prices");
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
                view_room_prices();
            } else if (choice == 11) {
                break;
            }else{
                System.out.println("Invalid choice");
            }
            System.out.println();
            System.out.println("--------------------Hotel Room Management System V1.0--------------------");
            for(int i=0;i<4;i++) {
                System.out.println();
            }
        }
    }
}