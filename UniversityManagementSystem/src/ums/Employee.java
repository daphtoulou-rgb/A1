package ums;

import java.util.Locale;

/**
 * An employee of the university. Every employee receives a unique, 7-digit
 * employee id (e.g. "0000001") when it is created.
 */
public abstract class Employee extends Person {

    /** Next employee number to hand out. Shared by all employees. */
    private static int nextEmployeeNumber = 1;

    /** Unique employee id. */
    private final String employeeId;

    /** Yearly salary in dollars. */
    private final double salary;

    /**
     * Creates an employee and assigns it the next available employee id.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param salary    the yearly salary
     */
    protected Employee(String firstName, String lastName, double salary) {
        super(firstName, lastName);
        this.employeeId = String.format("%07d", nextEmployeeNumber++);
        this.salary = salary;
    }

    /**
     * Returns the unique employee id.
     *
     * @return the employee id
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Returns the yearly salary.
     *
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Returns the salary formatted as a dollar amount (e.g. "$85,000.00").
     *
     * @return the formatted salary
     */
    protected String getFormattedSalary() {
        return String.format(Locale.ROOT, "$%,.2f", salary);
    }
}
