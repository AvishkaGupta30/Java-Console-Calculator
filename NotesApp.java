
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n=== Notes App ===");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Edit Note");
            System.out.println("4. Delete Note");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> addNote(scanner);
                case 2 -> viewNotes();
                case 3 -> editNote(scanner);
                case 4 -> deleteNote(scanner);
                case 0 -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addNote(Scanner scanner) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            System.out.print("Enter your note: ");
            String note = scanner.nextLine();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            out.println(dateTime + " | " + note);
            System.out.println("Note saved successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No notes found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int index = 1;
            System.out.println("\n--- Your Notes ---");
            while ((line = br.readLine()) != null) {
                System.out.println(index + ". " + line);
                index++;
            }
            System.out.println("------------------");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void editNote(Scanner scanner) {
        List<String> notes = readNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes to edit.");
            return;
        }

        viewNotes();
        System.out.print("Enter note number to edit: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > notes.size()) {
            System.out.println("Invalid note number.");
            return;
        }

        System.out.print("Enter new note: ");
        String newNote = scanner.nextLine();
        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        notes.set(num - 1, dateTime + " | " + newNote);

        saveNotes(notes);
        System.out.println("Note updated successfully.");
    }

    private static void deleteNote(Scanner scanner) {
        List<String> notes = readNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes to delete.");
            return;
        }

        viewNotes();
        System.out.print("Enter note number to delete: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > notes.size()) {
            System.out.println("Invalid note number.");
            return;
        }

        notes.remove(num - 1);
        saveNotes(notes);
        System.out.println("Note deleted successfully.");
    }

    private static List<String> readNotes() {
        List<String> notes = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return notes;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                notes.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return notes;
    }

    private static void saveNotes(List<String> notes) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
            for (String note : notes) {
                out.println(note);
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
