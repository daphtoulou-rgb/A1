package ums;

import java.util.ArrayList;
import java.util.List;

/**
 * An administrator is an employee who is assigned administrative tasks.
 */
public class Administrator extends Employee {

    /** Tasks assigned to this administrator. */
    private final List<String> tasks = new ArrayList<>();

    /**
     * Creates an administrator.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param salary    the yearly salary
     */
    public Administrator(String firstName, String lastName, double salary) {
        super(firstName, lastName, salary);
    }

    /**
     * Adds a task to this administrator.
     *
     * @param task the description of the task
     */
    public void addTask(String task) {
        tasks.add(task);
    }

    /**
     * Returns the administrator's tasks, one task per line.
     *
     * @return the formatted list of tasks
     */
    private String getTasksList() {
        if (tasks.isEmpty()) {
            return "    (none)\n";
        }
        StringBuilder sb = new StringBuilder();
        for (String task : tasks) {
            sb.append("    - ").append(task).append('\n');
        }
        return sb.toString();
    }

    /**
     * Returns a multi-line description of the administrator.
     *
     * @return the string representation of the administrator
     */
    @Override
    public String toString() {
        return "Administrator " + getFullName() + " (Employee ID: " + getEmployeeId() + ")\n"
                + "  Salary: " + getFormattedSalary() + "\n"
                + "  Tasks:\n"
                + getTasksList();
    }
}
