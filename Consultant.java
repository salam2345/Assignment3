/*
  SENG 3120 Course material
  Copyright (c) 2025
  All rights reserved.

  This document contains resources for homework assigned to students of
  SENG 3120 and shall not be distributed without permission.  Posting this
  file to a public or private website, or providing this file to a person
  not registered in SENG 3120, constitutes Academic Misconduct, according
  to Thompsons Rivers University Policy on Academic Misconduct.

  Synopsis:
     Consultant class for Assignment 3
 */

/**
 * A model of a Consultant, which is a specialized type of Manager.
 * A Consultant has a name, social insurance number, employee ID, and a list of students.
 * Extends the Manager class.
 */
public class Consultant extends Manager {

    /**
     * Initialize a Consultant with the given name, social insurance number, and employee ID.
     * Initially the Consultant has no students.
     *
     * @param name          the name of the consultant
     * @param ssn           the social insurance number of the consultant
     * @param employeeId    the employee ID of the consultant
     * @precond name != null && !name.equals("") && ssn > 0 && employeeId != null && !employeeId.equals("")
     */
    public Consultant(String name, int ssn, String employeeId) {
        super(name, ssn, employeeId);
    }

    /**
     * Return a string representation of all information about this consultant.
     * The string is prefixed with "Consultant: " to distinguish from regular managers.
     *
     * @return a string representation of the consultant
     */
    public String toString() {
        return "Consultant: " + super.toString();
    }

    /**
     * A main method to test the Consultant class.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        // Create a consultant
        Consultant c = new Consultant("Dr. Carol White", 999888777, "CON001");
        System.out.println("--- Initial Consultant State ---");
        System.out.println(c);

        // Add students
        Student s1 = new Student("John Doe", 987654321, "SID001");
        Student s2 = new Student("Jane Doe", 123456789, "SID002");
        c.addStudent(s1);
        c.addStudent(s2);
        System.out.println("--- After Adding Students ---");
        System.out.println(c);

        // Test hasStudent
        System.out.println("Has SID001: " + c.hasStudent("SID001")); // true
        System.out.println("Has SID999: " + c.hasStudent("SID999")); // false

        // Remove a student
        c.removeStudent("SID002");
        System.out.println("--- After Removing SID002 ---");
        System.out.println(c);
    }
}