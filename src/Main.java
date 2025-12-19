import model.Student;
import service.StudentManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager("data/students.dat");

        while (true) {

            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice;
try {
    choice = Integer.parseInt(sc.nextLine());
} catch (NumberFormatException e) {
    System.out.println("Please enter a valid number.");
    continue;
}


            switch (choice) {
                case 1 -> {
                    int id;
try {
    System.out.print("Enter ID: ");
    id = Integer.parseInt(sc.nextLine());
} catch (NumberFormatException e) {
    System.out.println("Invalid ID. Must be a number.");
    return;
}


                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
if (name.isBlank()) {
    System.out.println("Name cannot be empty.");
    return;
}
                    int age;
try {
    System.out.print("Enter Age: ");
    age = Integer.parseInt(sc.nextLine());
    if (age <= 0) {
        System.out.println("Age must be greater than 0.");
        return;
    }
} catch (NumberFormatException e) {
    System.out.println("Invalid age.");
    return;
}


                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();

                    manager.addStudent(new Student(id, name, age, course));
                    System.out.println("Student Added!");
                }

                case 2 -> {
                    System.out.println("\nAll Students:");
                    manager.getAllStudents().forEach(System.out::println);
                }

                case 3 -> {
                    System.out.print("Enter ID: ");
                    int searchId = Integer.parseInt(sc.nextLine());

                    Student s = manager.getStudentById(searchId);
                    if (s != null)
                        System.out.println(s);
                    else
                        System.out.println("Not Found!");
                }

                case 4 -> {
                    System.out.print("Enter ID to Update: ");
                    int updateId = Integer.parseInt(sc.nextLine());

                    System.out.print("New Name: ");
                    String name = sc.nextLine();

                    System.out.print("New Age: ");
                    int age = Integer.parseInt(sc.nextLine());

                    System.out.print("New Course: ");
                    String course = sc.nextLine();

                    boolean updated = manager.updateStudent(new Student(updateId, name, age, course));
                    System.out.println(updated ? "Updated!" : "Student not found!");
                }

                case 5 -> {
                    System.out.print("Enter ID to Delete: ");
                    int deleteId = Integer.parseInt(sc.nextLine());

                    boolean deleted = manager.deleteStudent(deleteId);
                    System.out.println(deleted ? "Deleted!" : "Student not found!");
                }

                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid Option!");
            }
        }
    }
}
