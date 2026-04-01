

import java.util.ArrayList;


public class Manager extends BasicManager {

    /**
     * The list of students assigned to this manager.
     */
    private ArrayList<Student> students;

    /**
     * Initialize a Manager with the given name, social insurance number, and employee ID.
     *  manager initially has no students.
     *
     * @param name           name of the manager
     * @param ssn            social insurance number of the manager
     * @param employeeId     employee ID of the manager
     * @precond name != null && !name.equals("") && ssn > 0 && employeeId != null && !employeeId.equals("")
     */
    public Manager(String name, int ssn, String employeeId) {
        super(name, ssn, employeeId);
        this.students = new ArrayList<Student>();
    }

    /**
     * Add a student to this manager's list of students.
     *
     * @param s  Student to add
     * @precond s != null
     */
    public void addStudent(Student s) {
        if (s == null)
            throw new IllegalArgumentException("Student cannot be null.");
        students.add(s);
    }

    /**
     * Removing the student with the given SID from this manager's list of students.
     *  nothing if no student with that SID is found.
     *
     * @param sid 
     * @precond sid != null && !sid.equals("")
     */
    public void removeStudent(String sid) {
        if (sid == null || sid.equals(""))
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getSID().equals(sid)) {
                students.remove(i);
                return;
            }
        }
    }

    /**
     * Check whether a student with the given SID is under this manager's care.
     *
     * @param sid  student ID to search for
     * @precond sid != null && !sid.equals("")
     * @return true if a student with the given SID is found, false otherwise
     */
    public boolean hasStudent(String sid) {
        if (sid == null || sid.equals(""))
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        for (Student s : students) {
            if (s.getSID().equals(sid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returning a string representation of all the information about the manager,
     * including name, SSN, employee ID, and all assigned students.
     *
     * @return 
     */
    public String toString() {
        String result = super.toString();
        result += "Students: ";
        if (students.isEmpty()) {
            result += "None\n";
        } else {
            result += "\n";
            for (Student s : students) {
                result += "  - " + s.getName() + " (SID: " + s.getSID() + ")\n";
            }
        }
        return result;
    }

    /**
     * A main method to test the Manager class.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        // Create a manager
        Manager mgr = new Manager("Alice Smith", 111222333, "EMP001");
        System.out.println("--- Initial Manager State ---");
        System.out.println(mgr);

        // Create students
        Student s1 = new Student("John Doe", 987654321, "SID001");
        Student s2 = new Student("Jane Doe", 123456789, "SID002");

        // Add students
        mgr.addStudent(s1);
        mgr.addStudent(s2);
        System.out.println("--- After Adding Students ---");
        System.out.println(mgr);

        // Check hasStudent
        System.out.println("Has SID001: " + mgr.hasStudent("SID001")); // true
        System.out.println("Has SID999: " + mgr.hasStudent("SID999")); // false

        // Remove a student
        mgr.removeStudent("SID001");
        System.out.println("--- After Removing SID001 ---");
        System.out.println(mgr);
    }
}