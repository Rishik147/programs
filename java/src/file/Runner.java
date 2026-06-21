package file;

import java.io.*;
import java.util.Arrays;

public class Runner {
    public static void main(String[] args) throws IOException {
        // Write content to file using BufferedWriter with try-with-resources
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Notes.txt", true))) {
            writer.newLine();
            writer.write("Hello");
            writer.newLine();
            writer.write("This is Max");
        } catch (Exception e) {
            // Handle any exceptions that occur during writing
            System.out.println("Exception occurred while writing to file: " + Arrays.toString(e.getStackTrace()));
        }

        // Read content from file and print each line using BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader("Notes.txt"))) {
            String content;
            // Read line by line until end of file
            while ((content = reader.readLine()) != null) {
                System.out.println(content);
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during reading
            System.out.println("Exception occurred while reading from file: " + Arrays.toString(e.getStackTrace()));
        }

    }
}
