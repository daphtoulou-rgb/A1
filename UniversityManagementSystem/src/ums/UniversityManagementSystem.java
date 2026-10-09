/*
 * Group Members:
 * Ngozi Onyechere - 300485967
 * Daphnee Toulou - 300501881
 */
package ums;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores the courses, employees and students of the university and processes
 * the commands received from the {@link CommandLineInterface}.
 * <p>
 * Every {@code process...} method receives the arguments of a command (the
 * command word and the entity word are removed). When a command cannot be
 * carried out, the method throws an {@link IllegalArgumentException} or an
 * {@link IllegalStateException} whose message is displayed to the user.
 */
public class UniversityManagementSystem {

    /** Courses, indexed by course code. */
    private final Map<String, Course> courses = new LinkedHashMap<>();

    /** Employees (professors, administrators, ...), indexed by employee id. */
    private final Map<String, Employee> employees = new LinkedHashMap<>();

    /** Students, indexed by student id. */
    private final Map<String, Student> students = new LinkedHashMap<>();

    /**
     * Creates an empty university management system.
     */
    public UniversityManagementSystem() {
        // Nothing to initialize: all collections start empty.
    }

    // ------------------------------------------------------------------
    // create
    // ------------------------------------------------------------------

    /**
     * Processes: {@code create course "<course code>" "<course description>" "<capacity>"}
     *
     * @param input the arguments of the command
     */
    public void processCreateCourse(List<String> input) {
        requireArguments(input, 3, "create course \"<course code>\" \"<course description>\" \"<capacity>\"");
        String code = input.get(0).toUpperCase();
        if (courses.containsKey(code)) {
            throw new IllegalArgumentException("Course " + code + " already exists.");
        }
        Course course = new Course(code, input.get(1), parseInteger(input.get(2), "capacity"));
        courses.put(code, course);
        System.out.println("Course created:");
        System.out.print(course);
    }

    /**
     * Processes: {@code create <entity> "<First name>" "<Last name>" "<salary>"}
     * where {@code <entity>} is a type of employee.
     *
     * @param entity the type of employee to create (e.g. "professor")
     * @param input  the arguments of the command
     */
    public void processCreateEmployee(String entity, List<String> input) {
        requireArguments(input, 3, "create " + entity + " \"<First name>\" \"<Last name>\" \"<salary>\"");
        String firstName = input.get(0);
        String lastName = input.get(1);
        double salary = parseSalary(input.get(2));

        Employee employee;
        switch (entity) {
            case "professor":
                employee = new Professor(firstName, lastName, salary);
                break;
            case "administrator":
                employee = new Administrator(firstName, lastName, salary);
                break;
            case "ta":
                employee = new TeachingAssistant(firstName, lastName, salary);
                break;
            default:
                throw new IllegalArgumentException("Creating a '" + entity + "' is not supported.");
        }
        employees.put(employee.getEmployeeId(), employee);
        System.out.println("Employee created:");
        System.out.print(employee);
    }

    /**
     * Processes: {@code create student "<First name>" "<Last name>" "<program of study>"}
     *
     * @param input the arguments of the command
     */
    public void processCreateStudent(List<String> input) {
        requireArguments(input, 3, "create student \"<First name>\" \"<Last name>\" \"<program of study>\"");
        Student student = new Student(input.get(0), input.get(1), input.get(2));
        students.put(student.getStudentId(), student);
        System.out.println("Student created:");
        System.out.print(student);
    }

    // ------------------------------------------------------------------
    // assign
    // ------------------------------------------------------------------

    /**
     * Processes: {@code assign <entity> "<employee id>" "<course code>"}
     * where {@code <entity>} is a type of instructor.
     *
     * @param entity the type of instructor to assign (e.g. "professor")
     * @param input  the arguments of the command
     */
    public void processAssignInstructor(String entity, List<String> input) {
        requireArguments(input, 2, "assign " + entity + " \"<employee id>\" \"<course code>\"");
        String employeeId = input.get(0);
        Course course = findCourse(input.get(1));

        switch (entity) {
            case "professor":
                Professor professor = findEmployee(employeeId, Professor.class, "professor");
                if (course.getProfessor() != null) {
                    throw new IllegalStateException(course.getCode() + " is already taught by "
                            + course.getProfessor().getFullName() + ".");
                }
                professor.addCourse(course);
                course.setProfessor(professor);
                System.out.println("Professor " + professor.getFullName()
                        + " assigned to " + course.getCode() + ".");
                break;
            case "ta":
                TeachingAssistant ta = findEmployee(employeeId, TeachingAssistant.class, "TA");
                course.addTeachingAssistant(ta);
                System.out.println("TA " + ta.getFullName()
                        + " assigned to " + course.getCode() + ".");
                break;
            default:
                throw new IllegalArgumentException("Assigning a '" + entity + "' is not supported.");
        }
    }

    /**
     * Processes: {@code assign student "<student id>" "<course code>"}
     *
     * @param input the arguments of the command
     */
    public void processAssignStudent(List<String> input) {
        requireArguments(input, 2, "assign student \"<student id>\" \"<course code>\"");
        Student student = findStudent(input.get(0));
        Course course = findCourse(input.get(1));
        course.registerStudent(student);
        System.out.println("Student " + student.getFullName()
                + " registered in " + course.getCode() + ".");
    }

    /**
     * Processes: {@code assign administrator "<employee id>" "<task>"}
     *
     * @param input the arguments of the command
     */
    public void processAssignAdministrator(List<String> input) {
        requireArguments(input, 2, "assign administrator \"<employee id>\" \"<task>\"");
        Administrator administrator = findEmployee(input.get(0), Administrator.class, "administrator");
        administrator.addTask(input.get(1));
        System.out.println("Task assigned to administrator " + administrator.getFullName() + ".");
    }

    /**
     * Processes: {@code assign grade "<student id>" "<course code>" "<grade>"}
     *
     * @param input the arguments of the command
     */
    public void processAssignGrade(List<String> input) {
        // 
        requireArguments(input, 3, "assign grade \"<student id>\" \"<course code>\" \"<grade>\"");
        Student student = findStudent(input.get(0));
        Course course = findCourse(input.get(1));
        int grade = parseInteger(input.get(2), "grade");
        student.addGrade(course.getCode(), grade);
        System.out.println("Grade " + grade + " (" + Student.toLetterGrade(grade) + ") recorded for " 
                + student.getFullName() + " in " + course.getCode() + ".");
    }

    // ------------------------------------------------------------------
    // list
    // ------------------------------------------------------------------

    /**
     * Processes: {@code list <entity>} where {@code <entity>} is
     * "employees" or a plural type of employee (e.g. "professors").
     *
     * @param entity the type of employees to list
     */
    public void processListEmployees(String entity) {
        Class<? extends Employee> type;
        switch (entity) {
            case "employees":
                type = Employee.class;
                break;
            case "professors":
                type = Professor.class;
                break;
            case "administrators":
                type = Administrator.class;
                break;
            case "tas":
                type = TeachingAssistant.class;
                break;
            default:
                throw new IllegalArgumentException("Listing '" + entity + "' is not supported.");
        }
        int count = 0;
        for (Employee employee : employees.values()) {
            if (type.isInstance(employee)) {
                System.out.print(employee);
                count++;
            }
        }
        System.out.println(count + " " + entity + " found.");
    }

    /**
     * Processes: {@code list students}
     */
    public void processListStudents() {
        for (Student student : students.values()) {
            System.out.print(student);
        }
        System.out.println(students.size() + " students found.");
    }

    /**
     * Processes: {@code list courses}
     */
    public void processListCourses() {
        for (Course course : courses.values()) {
            System.out.print(course);
        }
        System.out.println(courses.size() + " courses found.");
    }

    // ------------------------------------------------------------------
    // get
    // ------------------------------------------------------------------

    /**
     * Processes: {@code get <entity> "<employee id>"} for every type of employee.
     *
     * @param input the arguments of the command
     */
    public void processGetEmployee(List<String> input) {
        requireArguments(input, 1, "get employee \"<employee id>\"");
        System.out.print(findEmployee(input.get(0), Employee.class, "employee"));
    }

    /**
     * Processes: {@code get student "<student id>"}
     *
     * @param input the arguments of the command
     */
    public void processGetStudent(List<String> input) {
        requireArguments(input, 1, "get student \"<student id>\"");
        System.out.print(findStudent(input.get(0)));
    }

    /**
     * Processes: {@code get course "<course code>"}
     *
     * @param input the arguments of the command
     */
    public void processGetCourse(List<String> input) {
        requireArguments(input, 1, "get course \"<course code>\"");
        System.out.print(findCourse(input.get(0)));
    }

    // ------------------------------------------------------------------
    // helpers
    // ------------------------------------------------------------------

    /**
     * Returns the course with the given code.
     *
     * @param code the course code (case-insensitive)
     * @return the course
     * @throws IllegalArgumentException if there is no such course
     */
    private Course findCourse(String code) {
        Course course = courses.get(code.toUpperCase());
        if (course == null) {
            throw new IllegalArgumentException("No course with code " + code + ".");
        }
        return course;
    }

    /**
     * Returns the student with the given id.
     *
     * @param studentId the student id
     * @return the student
     * @throws IllegalArgumentException if there is no such student
     */
    private Student findStudent(String studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            throw new IllegalArgumentException("No student with id " + studentId + ".");
        }
        return student;
    }

    /**
     * Returns the employee with the given id, provided it is of the expected type.
     *
     * @param <T>        the expected type of employee
     * @param employeeId the employee id
     * @param type       the expected class of the employee
     * @param label      the name of the expected type, used in the error message
     * @return the employee
     * @throws IllegalArgumentException if there is no such employee of the expected type
     */
    private <T extends Employee> T findEmployee(String employeeId, Class<T> type, String label) {
        Employee employee = employees.get(employeeId);
        if (!type.isInstance(employee)) {
            throw new IllegalArgumentException("No " + label + " with id " + employeeId + ".");
        }
        return type.cast(employee);
    }

    /**
     * Checks that a command received the expected number of arguments.
     *
     * @param input    the arguments of the command
     * @param expected the expected number of arguments
     * @param usage    the correct usage of the command
     * @throws IllegalArgumentException if the number of arguments is wrong
     */
    private static void requireArguments(List<String> input, int expected, String usage) {
        if (input.size() != expected) {
            throw new IllegalArgumentException("Usage: " + usage);
        }
    }

    /**
     * Parses a positive integer.
     *
     * @param value the text to parse
     * @param label the name of the value, used in the error message
     * @return the integer value
     * @throws IllegalArgumentException if the text is not an integer
     */
    private static int parseInteger(String value, String label) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + label + ": " + value);
        }
    }

    /**
     * Parses a non-negative salary.
     *
     * @param value the text to parse
     * @return the salary
     * @throws IllegalArgumentException if the text is not a valid salary
     */
    private static double parseSalary(String value) {
        try {
            double salary = Double.parseDouble(value.trim());
            if (!Double.isFinite(salary)) {
                throw new IllegalArgumentException("Invalid salary: " + value);
            }
            if (salary < 0) {
                throw new IllegalArgumentException("A salary cannot be negative.");
            }
            return salary;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid salary: " + value);
        }
    }
}
