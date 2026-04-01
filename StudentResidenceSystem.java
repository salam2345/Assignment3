
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;


public class StudentResidenceSystem {


    private Residence residence;

  
    private TreeMap<String, Student> students;


    private TreeMap<String, Manager> managers;

    /**
     * Scanner for reading user input from the console.
     */
    private Scanner scanner;


    public StudentResidenceSystem() {
        students = new TreeMap<String, Student>();
        managers = new TreeMap<String, Manager>();
        scanner = new Scanner(System.in);

        // Get residence details from the user
        System.out.print("Enter the name of the residence: ");
        String resName = scanner.nextLine().trim();

        System.out.print("Enter the label of the first bed: ");
        int minBed = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter the label of the last bed: ");
        int maxBed = Integer.parseInt(scanner.nextLine().trim());

        residence = new Residence(resName, minBed, maxBed);
        System.out.println("Residence '" + resName + "' created with beds " + minBed + " to " + maxBed + ".\n");
    }

    /**
     * Execute Task 2: Add a new student to the system (no bed assignment yet).
     * Prompts the user for the student's name, SSN, and SID.
     */
    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter student SSN: ");
        int ssn = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter student SID: ");
        String sid = scanner.nextLine().trim();

        if (students.containsKey(sid)) {
            System.out.println("A student with SID " + sid + " already exists.");
            return;
        }

        Student s = new Student(name, ssn, sid);
        students.put(sid, s);
        System.out.println("Student " + name + " (SID: " + sid + ") added to the system.\n");
    }

    /**
     * Execute Task 3: Add a new manager to the system.
     * Prompts the user for the manager's name, SSN, employee ID, and whether they are a Consultant.
     */
    public void addManager() {
        System.out.print("Enter manager name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter manager SSN: ");
        int ssn = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter manager employee ID: ");
        String empId = scanner.nextLine().trim();

        if (managers.containsKey(empId)) {
            System.out.println("A manager with employee ID " + empId + " already exists.");
            return;
        }

        System.out.print("Is this manager a Consultant? (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        Manager m;
        if (answer.equals("yes") || answer.equals("y")) {
            m = new Consultant(name, ssn, empId);
            System.out.println("Consultant " + name + " (ID: " + empId + ") added to the system.\n");
        } else {
            m = new Manager(name, ssn, empId);
            System.out.println("Manager " + name + " (ID: " + empId + ") added to the system.\n");
        }
        managers.put(empId, m);
    }

    /**
     * Execute Task 4: Assign a manager to a student.
     * Adds the student to the manager's list and adds the manager to the student's list.
     * Prompts the user for the student's SID and the manager's employee ID.
     */
    public void assignManagerToStudent() {
        System.out.print("Enter student SID: ");
        String sid = scanner.nextLine().trim();

        System.out.print("Enter manager employee ID: ");
        String empId = scanner.nextLine().trim();

        if (!students.containsKey(sid)) {
            System.out.println("No student with SID " + sid + " found.");
            return;
        }
        if (!managers.containsKey(empId)) {
            System.out.println("No manager with employee ID " + empId + " found.");
            return;
        }

        Student s = students.get(sid);
        Manager m = managers.get(empId);

        // Check if already associated
        if (s.hasManager(empId)) {
            System.out.println("Manager " + empId + " is already assigned to student " + sid + ".");
            return;
        }

        // Create the two-way association
        s.addManager(m);
        m.addStudent(s);
        System.out.println("Manager " + m.getName() + " assigned to student " + s.getName() + ".\n");
    }

    /**
     * Execute Task 5 (stub): Display the empty beds of the residence.
     * This method is a stub and does not have a full implementation.
     */
    public void displayEmptyBeds() {
        // Stub: to be implemented in a future assignment
        System.out.println("displayEmptyBeds() is not yet fully implemented.\n");
    }

    /**
     * Execute Task 6: Assign a student to a bed.
     * Prompts the user for the student's SID and the bed label.
     */
    public void assignBed() {
        System.out.print("Enter student SID: ");
        String sid = scanner.nextLine().trim();

        if (!students.containsKey(sid)) {
            System.out.println("No student with SID " + sid + " found.");
            return;
        }

        Student s = students.get(sid);

        if (s.getBedLabel() != -1) {
            System.out.println("Student " + s.getName() + " is already assigned to bed " + s.getBedLabel() + ".");
            return;
        }

        // Show available beds to help the user choose
        ArrayList<Integer> available = residence.availableBeds();
        if (available.isEmpty()) {
            System.out.println("No available beds in the residence.");
            return;
        }
        System.out.println("Available beds: " + available);

        System.out.print("Enter bed label: ");
        int bedLabel = Integer.parseInt(scanner.nextLine().trim());

        if (!residence.isValidLabel(bedLabel)) {
            System.out.println("Bed label " + bedLabel + " is not valid.");
            return;
        }
        if (residence.isOccupied(bedLabel)) {
            System.out.println("Bed " + bedLabel + " is already occupied.");
            return;
        }

        residence.assignStudentToBed(s, bedLabel);
        System.out.println("Student " + s.getName() + " assigned to bed " + bedLabel + ".\n");
    }

    /**
     * Execute Task 7 (stub): Release a student from the residence.
     * This method is a stub and does not have a full implementation.
     */
    public void releaseStudent() {
        // Stub: to be implemented in a future assignment
        System.out.println("releaseStudent() is not yet fully implemented.\n");
    }

    /**
     * Execute Task 8: Drop the manager-student association.
     * Removes the student from the manager's list and the manager from the student's list.
     * Prompts the user for the student's SID and the manager's employee ID.
     */
    public void dropAssociation() {
        System.out.print("Enter student SID: ");
        String sid = scanner.nextLine().trim();

        System.out.print("Enter manager employee ID: ");
        String empId = scanner.nextLine().trim();

        if (!students.containsKey(sid)) {
            System.out.println("No student with SID " + sid + " found.");
            return;
        }
        if (!managers.containsKey(empId)) {
            System.out.println("No manager with employee ID " + empId + " found.");
            return;
        }

        Student s = students.get(sid);
        Manager m = managers.get(empId);

        if (!s.hasManager(empId)) {
            System.out.println("Manager " + empId + " is not currently assigned to student " + sid + ".");
            return;
        }

        // Remove both sides of the association
        s.removeManager(empId);
        m.removeStudent(sid);
        System.out.println("Association between manager " + m.getName()
                + " and student " + s.getName() + " has been removed.\n");
    }

    /**
     * Execute Task 9: Display the current system state.
     * Prints all students, all managers, and all bed assignments.
     */
    public void systemState() {
        System.out.println(this.toString());
    }

    /**
     * Return a string representation of the entire StudentResidenceSystem,
     * including the residence, all students, and all managers.
     *
     * @return a string representation of the system
     */
    public String toString() {
        String result = "=== Student Residence System State ===\n";

        // Residence info
        result += residence.toString();

        // All students
        result += "\n--- Students ---\n";
        if (students.isEmpty()) {
            result += "No students in the system.\n";
        } else {
            for (Student s : students.values()) {
                result += s.toString() + "\n";
            }
        }

        // All managers
        result += "\n--- Managers ---\n";
        if (managers.isEmpty()) {
            result += "No managers in the system.\n";
        } else {
            for (Manager m : managers.values()) {
                result += m.toString() + "\n";
            }
        }

        return result;
    }

    /**
     * Creates a StudentResidenceSystem and runs the interactive
     * menu loop until the user chooses to quit (task 1).
     * when quitting, displays the current system state.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        StudentResidenceSystem system = new StudentResidenceSystem();

        int choice = 0;

        // Menu loop
        while (choice != 1) {
            // Display menu
            System.out.println("=== Residence Management Menu ===");
            System.out.println("1. Quit");
            System.out.println("2. Add a new student");
            System.out.println("3. Add a new manager");
            System.out.println("4. Assign a manager to a student");
            System.out.println("5. Display empty beds");
            System.out.println("6. Assign a student to a bed");
            System.out.println("7. Release a student from the residence");
            System.out.println("8. Drop manager-student association");
            System.out.println("9. Display current system state");
            System.out.print("Enter your choice (1-9): ");

            try {
                choice = Integer.parseInt(system.scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    // Quit — print system state before exiting
                    System.out.println("\nExiting the system...");
                    system.systemState();
                    break;
                case 2:
                    system.addStudent();
                    break;
                case 3:
                    system.addManager();
                    break;
                case 4:
                    system.assignManagerToStudent();
                    break;
                case 5:
                    system.displayEmptyBeds();
                    break;
                case 6:
                    system.assignBed();
                    break;
                case 7:
                    system.releaseStudent();
                    break;
                case 8:
                    system.dropAssociation();
                    break;
                case 9:
                    system.systemState();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.\n");
            }
        }
    }
}