package ums;

/**
 * Root of the hierarchy of people stored in the University Management System.
 */
public abstract class Person {

    /** First name of the person. */
    private final String firstName;

    /** Last name of the person. */
    private final String lastName;

    /**
     * Creates a person.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    protected Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the first name of the person.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the person.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the full name of the person ("First Last").
     *
     * @return the full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
