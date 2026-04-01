
public class BasicManager extends Person {

    /**
     * The employee id of the  manager.
     */

    private String employeeId;
    /**
     * Initialize an instance of a Basic Manager with the given name, social insurance number and employee id.
     * @param name   name of the manager
     * @param ssn    social insurance number of the manager
     * @param employeeId     id of the manager
     * @precond name != null && !name.equals("") && ssn > 0 && employeeId != null && !employeeId.equals("")
     */
    public BasicManager(String name, int ssn, String employeeId)
    {
        super(name,ssn);
        this.employeeId= employeeId;
    }
    /**
     * Returning  the employee Id of the manager
     *
     * @return  
     */
    public String getEmployeeId()
    {
        return this.employeeId;
    }

    /**
     * Returning a string representation of the Manager
     *
     * @return 
     */
    public String toString()
    {
        return super.toString() + "Employee Id: "+ this.getEmployeeId() + "\n";
    }
  
}