
import java.util.ArrayList;


public class Student extends Person {

    /**
     *  student's unique student ID (SID).
     */
    private String sid;

    /**
     *  label of the bed assigned to this student. -1 if no bed is assigned.
     */
    private int bedLabel;

    /**
     *  list of managers assigned to this student.
     */
    private ArrayList<Manager> managers;

    /**
     *
     * @param name   name of the student
     * @param ssn    social insurance number of the student
     * @param sid    student ID of the student
     * @precond name != null && !name.equals("") && ssn > 0 && sid != null && !sid.equals("")
     */
    public Student(String name, int ssn, String sid) {
        super(name, ssn);
        if (sid == null || sid.equals(""))
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        this.sid = sid;
        this.bedLabel = -1;
        this.managers = new ArrayList<Manager>();
    }

    /**
     * Return the student ID of the student.
     *
     * @return 
     */
    public String getSID() {
        return this.sid;
    }

    /**
     * Return the bed label of the student. Returns -1 if no bed is assigned.
     *
     * @return 
     */
    public int getBedLabel() {
        return this.bedLabel;
    }

    /**
     * Set the bed label for the student.
     *
     * @param bedLabel 
     */
    public void setBedLabel(int bedLabel) {
        this.bedLabel = bedLabel;
    }

    /**
     * Add a manager to this student's list of managers.
     *
     * @param m  Manager to add
     * @precond m != null
     */
    public void addManager(Manager m) {
        if (m == null)
            throw new IllegalArgumentException("Manager cannot be null.");
        managers.add(m);
    }

    /**
     * Remove the manager with the given employee ID from this student's list of managers.
     * Does nothing if no manager with that ID is found.
     *
     * @param employeeId  employee ID of the manager to remove
     * @precond employeeId != null && !employeeId.equals("")
     */
    public void removeManager(String employeeId) {
        if (employeeId == null || employeeId.equals(""))
            throw new IllegalArgumentException("Employee ID cannot be null or empty.");
        for (int i = 0; i < managers.size(); i++) {
            if (managers.get(i).getEmployeeId().equals(employeeId)) {
                managers.remove(i);
                return;
            }
        }
    }

    /**
     * Check whether a manager with the given employee ID is assigned to this student.
     *
     * @param employeeId 
     * @precond employeeId != null && !employeeId.equals("")
     * @return true if a manager with the given employee ID is found, false otherwise
     */
    public boolean hasManager(String employeeId) {
        if (employeeId == null || employeeId.equals(""))
            throw new IllegalArgumentException("Employee ID cannot be null or empty.");
        for (Manager m : managers) {
            if (m.getEmployeeId().equals(employeeId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a string representation of the student
     */
    public String toString() {
        String result = super.toString();
        result += "Student ID: " + this.sid + "\n";
        if (this.bedLabel == -1) {
            result += "Bed: Not assigned\n";
        } else {
            result += "Bed: " + this.bedLabel + "\n";
        }
        result += "Managers: ";
        if (managers.isEmpty()) {
            result += "None\n";
        } else {
            result += "\n";
            for (Manager m : managers) {
                result += "  - " + m.getName() + " (ID: " + m.getEmployeeId() + ")\n";
            }
        }
        return result;
    }

    /**
     * A main method to test the Student class.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        // Create two managers for testing
        Manager mgr1 = new Manager("Alice Smith", 111222333, "EMP001");
        Manager mgr2 = new Manager("Bob Jones", 444555666, "EMP002");

        // Create a student
        Student s = new Student("John Doe", 987654321, "SID001");
        System.out.println("--- Initial Student State ---");
        System.out.println(s);

        // Assign a bed
        s.setBedLabel(5);
        System.out.println("--- After Bed Assignment ---");
        System.out.println(s);

        // Add managers
        s.addManager(mgr1);
        s.addManager(mgr2);
        System.out.println("--- After Adding Managers ---");
        System.out.println(s);

        // Check hasManager
        System.out.println("Has EMP001: " + s.hasManager("EMP001")); // true
        System.out.println("Has EMP999: " + s.hasManager("EMP999")); // false

        // Remove a manager
        s.removeManager("EMP001");
        System.out.println("--- After Removing EMP001 ---");
        System.out.println(s);
    }
}