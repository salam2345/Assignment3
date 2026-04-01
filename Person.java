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
 * The model of a person who has a name and a health number
 * that cannot be changed.
 */
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
     * Initialize an instance of a Person with the given name and health number.
     *
     * @param pName the person's name
     * @param ssn the person's social Insurance number
     */
    public Person(String pName, int ssn) {
        this.name = pName;
        this.ssn = ssn;
    }

    /**
     * Return the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the social insurance number of the person.
     *
     * @return the ssn of the person
     */
    public int getSSN() {
        return this.ssn;
    }

    /**
     * Change the name of the person.
     *
     * @param newName the name of the person
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Return a string representation of the person.
     *
     * @return a string representation of the person
     */
    public String toString() {
        return "\nName: " + this.name + "\nSSN: " + this.ssn +"\n";
    }


}