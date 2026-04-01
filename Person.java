
public class Person {

    /**
     * The name of the person.
     */
    private String name;

    /**
     * The person's social insurance number.
     */
    private int ssn;

    /**
     * Initializ an instance of a Person with the given name and health number.
     *
     * @param pName  person's name
     * @param ssn  person's social Insurance number
     */
    public Person(String pName, int ssn) {
        this.name = pName;
        this.ssn = ssn;
    }

    /**
     * Return the name of the person.
     *
     * @return 
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the social insurance number of the person.
     *
     * @return 
     */
    public int getSSN() {
        return this.ssn;
    }

    /**
     * Change the name of the person.
     *
     * @param newName 
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Return a string representation of the person.
     *
     * @return 
     */
    public String toString() {
        return "\nName: " + this.name + "\nSSN: " + this.ssn +"\n";
    }


}