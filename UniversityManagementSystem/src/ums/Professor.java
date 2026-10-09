package ums;

/**
 * A professor is an instructor who can teach up to {@value #MAX_COURSES} courses at a time.
 */
public class Professor extends Instructor {

    /** Maximum number of courses a professor can teach at a time. */
    public static final int MAX_COURSES = 5;

    /**
     * Creates a professor.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param salary    the yearly salary
     */
    public Professor(String firstName, String lastName, double salary) {
        super(firstName, lastName, salary);
    }

    /**
     * Returns the maximum number of courses a professor can teach at a time.
     *
     * @return {@value #MAX_COURSES}
     */
    @Override
    public int getMaxCourses() {
        return MAX_COURSES;
    }

    /**
     * Returns a multi-line description of the professor.
     *
     * @return the string representation of the professor
     */
    @Override
    public String toString() {
        return "Professor " + getFullName() + " (Employee ID: " + getEmployeeId() + ")\n"
                + "  Salary: " + getFormattedSalary() + "\n"
                + "  Courses (" + getCourses().size() + "/" + getMaxCourses() + "):\n"
                + getCoursesList();
    }
}
