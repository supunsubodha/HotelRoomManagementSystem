//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
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
        // Convert ArrayList to Array
        String[] linesArray = new String[allrooms.size()];
        allrooms.toArray(linesArray);
        System.out.println("All rooms: " + allrooms);
    }
}