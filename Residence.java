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
     Updated Residence class for Assignment 3
     - Changed beds array type from Person[] to Student[]
     - Added availableBeds() method
     - Added freeBed() method
 */

import java.util.ArrayList;

/**
 * A Residence of a university with a specified number of beds with consecutive labels.
 * Each bed can hold a Student. Provides methods to assign, release, and query beds.
 */
public class Residence {

    /**
     * The name of this Residence.
     */
    private String name;

    /**
     * The (external) label of the first bed of the Residence.
     */
    private int minBedLabel;

    /**
     * An array to represent the beds of the Residence. Each bed is empty (null)
     * or else has a Student in it.
     */
    private Student[] beds;

    /**
     * Initialize the Residence with the name given, and with beds whose labels are
     * the consecutive integers from minBedLabel to maxBedLabel.
     *
     * @param wName         the name of the Residence
     * @param wMinBedLabel  the label of the first bed in the Residence
     * @param wMaxBedLabel  the label of the last bed in the Residence
     * @precond wName != null && !wName.equals("") && wMinBedLabel >= 0 && wMaxBedLabel >= wMinBedLabel
     */
    public Residence(String wName, int wMinBedLabel, int wMaxBedLabel) {
        if (wName == null || wName.equals(""))
            throw new IllegalArgumentException("The name of a Residence cannot be null or empty.  "
                    + "It is " + wName);
        if (wMinBedLabel < 0 || wMaxBedLabel < wMinBedLabel)
            throw new IllegalArgumentException("The bed labels " + wMinBedLabel + " and " + wMaxBedLabel
                    + " are invalid as they cannot be negative, and must have at least one bed.");

        name = wName;
        minBedLabel = wMinBedLabel;
        beds = new Student[wMaxBedLabel - wMinBedLabel + 1];
    }

    /**
     * Return the name of this Residence.
     *
     * @return the name of this Residence
     */
    public String getName() {
        return name;
    }

    /**
     * Return the smallest label for a bed in the Residence.
     *
     * @return the smallest label for a bed in the Residence
     */
    public int getMinBedLabel() {
        return minBedLabel;
    }

    /**
     * Return the largest label for a bed in the Residence.
     *
     * @return the largest label for a bed in the Residence
     */
    public int getMaxBedLabel() {
        return minBedLabel + beds.length - 1;
    }

    /**
     * Return the internal array index for the given external bed label.
     *
     * @param bedLabel the external bed label
     * @precond isValidLabel(bedLabel)
     * @return the internal array index corresponding to the external label
     */
    private int externalToInternalIndex(int bedLabel) {
        if (!isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the Residence.");
        return bedLabel - minBedLabel;
    }

    /**
     * Return the external bed label for the given internal array index.
     *
     * @param arrayIndex the internal array index
     * @precond 0 <= arrayIndex < beds.length
     * @return the external label corresponding to the internal array index
     */
    private int internalToExternalLabel(int arrayIndex) {
        if (arrayIndex < 0 || arrayIndex >= beds.length)
            throw new IllegalArgumentException("The value " + arrayIndex
                    + " is not a valid index for an array of length " + beds.length + ".");
        return arrayIndex + minBedLabel;
    }

    /**
     * Is the specified bed currently occupied?
     *
     * @param bedLabel the label of the bed to check
     * @precond isValidLabel(bedLabel)
     * @return true if the bed is occupied, false otherwise
     */
    public boolean isOccupied(int bedLabel) {
        if (!isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the Residence.");
        return beds[externalToInternalIndex(bedLabel)] != null;
    }

    /**
     * Return the Student in the specified bed.
     *
     * @param bedLabel the label of the bed from which to retrieve the student
     * @precond isValidLabel(bedLabel) && isOccupied(bedLabel)
     * @return the Student in the specified bed
     */
    public Student getStudent(int bedLabel) {
        if (!isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the Residence.");
        if (!isOccupied(bedLabel))
            throw new IllegalStateException("Bed " + bedLabel + " is not currently occupied"
                    + " so cannot get its student.");
        return beds[externalToInternalIndex(bedLabel)];
    }

    /**
     * Assign the specified student to the specified bed.
     *
     * @param s         the Student to be assigned a bed
     * @param bedLabel  the label of the bed to assign the student to
     * @precond isValidLabel(bedLabel) && !isOccupied(bedLabel)
     */
    public void assignStudentToBed(Student s, int bedLabel) {
        if (!isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the Residence.");
        if (isOccupied(bedLabel))
            throw new IllegalStateException("Bed " + bedLabel + " is currently occupied by "
                    + beds[externalToInternalIndex(bedLabel)].getName()
                    + " so cannot be assigned to another student.");

        beds[externalToInternalIndex(bedLabel)] = s;
        // Update the student's own bed label record
        s.setBedLabel(bedLabel);
    }

    /**
     * Return a list of the labels of all empty (unoccupied) beds in the Residence.
     *
     * @return an ArrayList of integer bed labels that are currently unoccupied
     */
    public ArrayList<Integer> availableBeds() {
        ArrayList<Integer> available = new ArrayList<Integer>();
        for (int i = 0; i < beds.length; i++) {
            if (beds[i] == null) {
                available.add(internalToExternalLabel(i));
            }
        }
        return available;
    }

    /**
     * Remove the Student from the specified bed, freeing it for future assignment.
     * The student's bed label is reset to -1.
     *
     * @param bedLabel the label of the bed to free
     * @precond isValidLabel(bedLabel) && isOccupied(bedLabel)
     */
    public void freeBed(int bedLabel) {
        if (!isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the Residence.");
        if (!isOccupied(bedLabel))
            throw new IllegalStateException("Bed " + bedLabel + " is not currently occupied"
                    + " so it cannot be freed.");

        // Reset the student's bed label before removing from bed
        beds[externalToInternalIndex(bedLabel)].setBedLabel(-1);
        beds[externalToInternalIndex(bedLabel)] = null;
    }

    /**
     * Is bedLabel a valid external label for a bed?
     *
     * @param bedLabel an int to be tested to determine whether it is a valid label
     *                 for a bed (from the external/user perspective)
     * @return true if bedLabel is a valid bed label, false otherwise
     */
    public boolean isValidLabel(int bedLabel) {
        return bedLabel >= minBedLabel && bedLabel <= minBedLabel + beds.length - 1;
    }

    /**
     * Return a String representation of the properties of the Residence,
     * including each bed and who occupies it (if anyone).
     *
     * @return a String representation of the properties of the Residence
     */
    public String toString() {
        String result = "\nResidence " + name + " with capacity " + beds.length
                + " has the following students: ";
        for (int i = 0; i < beds.length; i++) {
            result = result + "\nbed " + internalToExternalLabel(i) + ": ";
            if (beds[i] != null)
                result = result + beds[i].getName();
        }
        return result + "\n";
    }
}