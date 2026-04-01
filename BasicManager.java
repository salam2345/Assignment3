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
     Starter File for Assignment 3
 */
 

/**
 * A very simple model of a Basic manager who has a name and an employee Id.
 */
public class BasicManager extends Person {

    /**
     * The employee id of the  manager.
     */

    private String employeeId;
    /**
     * Initialize an instance of a Basic Manager with the given name, social insurance number and employee id.
     * @param name  the name of the manager
     * @param ssn   the social insurance number of the manager
     * @param employeeId    the id of the manager
     * @precond name != null && !name.equals("") && ssn > 0 && employeeId != null && !employeeId.equals("")
     */
    public BasicManager(String name, int ssn, String employeeId)
    {
        super(name,ssn);
        this.employeeId= employeeId;
    }
    /**
     * Return  the employee Id of the manager
     *
     * @return the employeeID of the Manager
     */
    public String getEmployeeId()
    {
        return this.employeeId;
    }

    /**
     * Return a string representation of the Manager
     *
     * @return a string representation of the Manager
     */
    public String toString()
    {
        return super.toString() + "Employee Id: "+ this.getEmployeeId() + "\n";
    }
    /**
     * A method to test the BasicDoctor class.
     */

}