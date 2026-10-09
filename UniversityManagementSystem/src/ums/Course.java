/*
 * Group Members:
 * Ngozi Onyechere - 300485967
 * Daphnee Toulou - 300501881
 */
package ums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A course offered by the university. A course has at most one professor and
 * a limited number of seats for students.
 */
public class Course {

    /** Unique course code (e.g. "SEG2105"). */
    private final String code;

    /** Short description of the course. */
    private final String description;

    /** Maximum number of students that can be registered. */
    private final int capacity;

    /** Professor teaching the course, or null if none is assigned yet. */
    private Professor professor;

    /** Students registered in the course. */
    private final List<Student> students = new ArrayList<>();

    /** Teaching assistants registered in the course. */
    private final List<TeachingAssistant> tas = new ArrayList<>();

    /**
     * Creates a course.
     *
     * @param code        the unique course code
     * @param description the course description
     * @param capacity    the maximum number of students
     * @throws IllegalArgumentException if the capacity is not positive
     */
    public Course(String code, String description, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The capacity of a course must be positive.");
        }
        this.code = code.toUpperCase();
        this.description = description;
        this.capacity = capacity;
    }

    /**
     * Returns the course code.
     *
     * @return the course code
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the course description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the maximum number of students.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the professor teaching the course.
     *
     * @return the professor, or null if none is assigned
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * Sets the professor teaching the course.
     *
     * @param professor the professor
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * Returns a read-only view of the registered students.
     *
     * @return the registered students
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    /**
     * Registers a student in the course.
     *
     * @param student the student to register
     * @throws IllegalStateException if the course is full or the student is already registered
     */
    public void registerStudent(Student student) {
        if (students.contains(student)) {
            throw new IllegalStateException(student.getFullName()
                    + " is already registered in " + code + ".");
        }
        if (students.size() >= capacity) {
            throw new IllegalStateException(code + " is full.");
        }
        students.add(student);
        student.addCourse(this);
    }

    /**
     * Registers a TA to the course.
     *
     * @param ta the TA to register
     * @throws IllegalStateException if there are already two TAs assigned or the same TA has already been assigned to the course.
     */
    public void addTeachingAssistant(TeachingAssistant ta) {
        if (tas.contains(ta)) {
            throw new IllegalStateException(ta.getFullName()
                    + " is already assigned to " + code + ".");
        }
        if (tas.size() >= 2) {
            throw new IllegalStateException(code + " already has the maximum of 2 TAs.");
        }
        tas.add(ta);
        ta.addCourse(this);
    }

    /**
     * Returns a multi-line description of the course.
     *
     * @return the string representation of the course
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(code).append(" - ").append(description).append('\n');
        sb.append("  Enrolment: ").append(students.size()).append('/').append(capacity).append('\n');
        sb.append("  Professor: ")
          .append(professor == null ? "(none)" : professor.getFullName()).append('\n');

        sb.append("  TAs: ");
        if (tas.isEmpty()) {
            sb.append("(none)\n");
        } else {
            for(int i = 0; i < tas.size(); i++) {
                sb.append(tas.get(i).getFullName());
                if (i < tas.size() - 1) {
                    sb.append(", "); //not last add comma
                }
            }
            sb.append('\n');
        }

        sb.append("  Students:\n");
        if (students.isEmpty()) {
            sb.append("    (none)\n");
        }
        for (Student student : students) {
            sb.append("    - ").append(student.getStudentId())
              .append(' ').append(student.getFullName()).append('\n');
        }
        return sb.toString();
    }
}
