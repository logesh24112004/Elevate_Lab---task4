import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== NOTES APP (FILE I/O) =====");
            System.out.println("1. Write Note");
            System.out.println("2. View Notes");
            System.out.println("3. Clear Notes");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    writeNote();
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    clearNotes();
                    break;
                case 4:
                    System.out.println("Exiting Notes App...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 4);

        sc.close();
    }

    // ------------------------ WRITE NOTE ------------------------
    public static void writeNote() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) { 
            fw.write(note + "\n");
            System.out.println("✔ Note saved successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error writing note: " + e.getMessage());
        }
    }

    // ------------------------ READ NOTES ------------------------
    public static void readNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            System.out.println("\n--- Saved Notes ---");
            while ((line = br.readLine()) != null) {
                System.out.println("• " + line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("❌ No notes found! (file missing)");
        } catch (IOException e) {
            System.out.println("❌ Error reading notes: " + e.getMessage());
        }
    }

    // ------------------------ CLEAR NOTES ------------------------
    public static void clearNotes() {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            fw.write("");  // overwrite file with empty content
            System.out.println("✔ Notes cleared!");
        } catch (IOException e) {
            System.out.println("❌ Error clearing notes: " + e.getMessage());
        }
    }
}
