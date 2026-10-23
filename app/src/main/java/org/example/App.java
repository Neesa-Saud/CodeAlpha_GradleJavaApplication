package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Task Manager ===");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Complete task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter task: ");
                    String task = scanner.nextLine();
                    tasks.add("[ ] " + task);
                    System.out.println("Task added!");
                    break;

                case "2":
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks found.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    }
                    break;

                case "3":
                    System.out.print("Enter task number: ");
                    try {
                        int number = Integer.parseInt(scanner.nextLine());

                        if (number >= 1 && number <= tasks.size()) {
                            String taskText = tasks.get(number - 1);

                            if (taskText.startsWith("[ ] ")) {
                                tasks.set(number - 1,
                                        "[✓] " + taskText.substring(4));
                                System.out.println("Task completed!");
                            } else {
                                System.out.println("Task is already completed.");
                            }
                        } else {
                            System.out.println("Invalid task number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                    break;

                case "4":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
