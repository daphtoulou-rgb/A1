package ums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An employee who can be assigned to teach courses. Each concrete kind of
 * instructor decides the maximum number of courses it can take at a time.
 */
public abstract class Instructor extends Employee {

    /** Courses currently assigned to this instructor. */
    private final List<Course> courses = new ArrayList<>();

    /**
     * Creates an instructor.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param salary    the yearly salary
     */
    protected Instructor(String firstName, String lastName, double salary) {
        super(firstName, lastName, salary);
    }

    /**
     * Returns the maximum number of courses this instructor can be assigned at a time.
     *
     * @return the maximum workload, in courses
     */
    public abstract int getMaxCourses();

    /**
     * Indicates whether this instructor can be assigned one more course.
     *
     * @return true if the instructor has not reached its maximum workload
     */
    public boolean canTakeCourse() {
        return courses.size() < getMaxCourses();
    }

    /**
     * Indicates whether this instructor is already assigned to the given course.
     *
     * @param course the course to check
     * @return true if the course is already assigned to this instructor
     */
    public boolean isAssignedTo(Course course) {
        return courses.contains(course);
    }

    /**
     * Adds a course to this instructor's workload.
     *
     * @param course the course to add
     * @throws IllegalStateException if the instructor has reached its maximum workload
     *                               or already has the course
     */
    public void addCourse(Course course) {
        if (isAssignedTo(course)) {
            throw new IllegalStateException(getFullName() + " is already assigned to "
                    + course.getCode() + ".");
        }
        if (!canTakeCourse()) {
            throw new IllegalStateException(getFullName() + " already has the maximum of "
                    + getMaxCourses() + " courses.");
        }
        courses.add(course);
    }

    /**
     * Returns a read-only view of the courses assigned to this instructor.
     *
     * @return the assigned courses
     */
    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    /**
     * Returns the list of assigned courses, one course per line.
     *
     * @return the formatted list of courses
     */
    protected String getCoursesList() {
        if (courses.isEmpty()) {
            return "    (none)\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Course course : courses) {
            sb.append("    - ").append(course.getCode())
              .append(": ").append(course.getDescription()).append('\n');
        }
        return sb.toString();
    }
}
