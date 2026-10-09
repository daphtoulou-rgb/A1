/*
 * Group Members:
 * Ngozi Onyechere - 300485967
 * Daphnee Toulou - 300501881
 */
package ums;
public class TeachingAssistant extends Instructor {

    public static final int MAX_COURSES = 2;

    /**
     * Creates a TA.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param salary    the yearly salary
     */
    public TeachingAssistant(String firstName, String lastName, double salary) {
        super(firstName, lastName, salary);
    }

    /**
     * Returns the maximum number of courses a TA can teach at a time.
     *
     * @return {@value #MAX_COURSES}
     */
    @Override
    public int getMaxCourses() {
        return MAX_COURSES;
    }

    /**
     * Returns a multi-line description of the TA.
     *
     * @return the string representation of the TA
     */
    @Override
    public String toString() {
        return "TA " + getFullName() + " (Employee ID: " + getEmployeeId() + ")\n"
                + "  Salary: " + getFormattedSalary() + "\n"
                + "  Courses (" + getCourses().size() + "/" + getMaxCourses() + "):\n"
                + getCoursesList();
    }
}
