package ums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Text-based user interface of the University Management System. It reads
 * commands from the standard input, splits them into words (text between
 * double quotes counts as a single word) and forwards them to the
 * {@link UniversityManagementSystem}.
 * <p>
 * <b>You do not need to modify this class.</b>
 */
public class CommandLineInterface {

    /** The system that processes the commands. */
    private final UniversityManagementSystem ums;

    /**
     * Creates a command line interface for the given system.
     *
     * @param ums the system that processes the commands
     */
    public CommandLineInterface(UniversityManagementSystem ums) {
        this.ums = ums;
    }

    /**
     * Starts the program.
     *
     * @param args command-line arguments (not used)
     * @throws IOException if the standard input cannot be read
     */
    public static void main(String[] args) throws IOException {
        new CommandLineInterface(new UniversityManagementSystem()).run();
    }

    /**
     * Reads and executes commands until "exit" or the end of the input.
     *
     * @throws IOException if the standard input cannot be read
     */
    public void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("University Management System. Type 'help' to see the commands.");
        while (true) {
            System.out.print("> ");
            String line = in.readLine();
            if (line == null) {
                System.out.println();
                break;
            }
            if (!execute(line)) {
                break;
            }
        }
        System.out.println("Goodbye!");
    }

    /**
     * Executes one command.
     *
     * @param line the command typed by the user
     * @return false if the user asked to exit, true otherwise
     */
    public boolean execute(String line) {
        // Ignore the byte-order mark that some shells (e.g. Windows PowerShell) add to piped input.
        List<String> words = tokenize(line.replace("﻿", ""));
        if (words.isEmpty()) {
            return true;
        }
        String command = words.get(0).toLowerCase();
        if (command.equals("exit") || command.equals("quit")) {
            return false;
        }
        if (command.equals("help")) {
            printHelp();
            return true;
        }
        if (words.size() < 2) {
            System.out.println("Error: incomplete command. Type 'help' to see the commands.");
            return true;
        }
        String entity = words.get(1).toLowerCase();
        List<String> arguments = words.subList(2, words.size());
        try {
            switch (command) {
                case "create":
                    create(entity, arguments);
                    break;
                case "assign":
                    assign(entity, arguments);
                    break;
                case "list":
                    list(entity);
                    break;
                case "get":
                    get(entity, arguments);
                    break;
                default:
                    System.out.println("Error: unknown command '" + command + "'.");
            }
        } catch (IllegalArgumentException | IllegalStateException
                 | UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    /**
     * Handles the "create" commands.
     *
     * @param entity    the type of entity to create
     * @param arguments the arguments of the command
     */
    private void create(String entity, List<String> arguments) {
        switch (entity) {
            case "course":
                ums.processCreateCourse(arguments);
                break;
            case "student":
                ums.processCreateStudent(arguments);
                break;
            case "professor":
            case "administrator":
            case "ta":
                ums.processCreateEmployee(entity, arguments);
                break;
            default:
                System.out.println("Error: cannot create '" + entity + "'.");
        }
    }

    /**
     * Handles the "assign" commands.
     *
     * @param entity    the type of entity to assign
     * @param arguments the arguments of the command
     */
    private void assign(String entity, List<String> arguments) {
        switch (entity) {
            case "professor":
            case "ta":
                ums.processAssignInstructor(entity, arguments);
                break;
            case "student":
                ums.processAssignStudent(arguments);
                break;
            case "administrator":
                ums.processAssignAdministrator(arguments);
                break;
            case "grade":
                ums.processAssignGrade(arguments);
                break;
            default:
                System.out.println("Error: cannot assign '" + entity + "'.");
        }
    }

    /**
     * Handles the "list" commands.
     *
     * @param entity the type of entities to list
     */
    private void list(String entity) {
        switch (entity) {
            case "courses":
                ums.processListCourses();
                break;
            case "students":
                ums.processListStudents();
                break;
            case "employees":
            case "professors":
            case "administrators":
            case "tas":
                ums.processListEmployees(entity);
                break;
            default:
                System.out.println("Error: cannot list '" + entity + "'.");
        }
    }

    /**
     * Handles the "get" commands.
     *
     * @param entity    the type of entity to get
     * @param arguments the arguments of the command
     */
    private void get(String entity, List<String> arguments) {
        switch (entity) {
            case "course":
                ums.processGetCourse(arguments);
                break;
            case "student":
                ums.processGetStudent(arguments);
                break;
            case "employee":
            case "professor":
            case "administrator":
            case "ta":
                ums.processGetEmployee(arguments);
                break;
            default:
                System.out.println("Error: cannot get '" + entity + "'.");
        }
    }

    /**
     * Splits a line into words. Text between double quotes is a single word.
     *
     * @param line the line to split
     * @return the list of words
     */
    static List<String> tokenize(String line) {
        List<String> words = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        boolean hasWord = false;
        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
                hasWord = true;
            } else if (Character.isWhitespace(c) && !inQuotes) {
                if (hasWord) {
                    words.add(current.toString());
                    current.setLength(0);
                    hasWord = false;
                }
            } else {
                current.append(c);
                hasWord = true;
            }
        }
        if (hasWord) {
            words.add(current.toString());
        }
        return words;
    }

    /**
     * Prints the list of supported commands.
     */
    private static void printHelp() {
        System.out.println(String.join("\n",
                "create course \"<course code>\" \"<course description>\" \"<capacity>\"",
                "create professor|administrator|TA \"<First name>\" \"<Last name>\" \"<salary>\"",
                "create student \"<First name>\" \"<Last name>\" \"<program of study>\"",
                "assign professor|TA \"<employee id>\" \"<course code>\"",
                "assign student \"<student id>\" \"<course code>\"",
                "assign administrator \"<employee id>\" \"<task>\"",
                "assign grade \"<student id>\" \"<course code>\" \"<grade>\"",
                "list employees|professors|administrators|TAs|students|courses",
                "get employee|professor|administrator|TA \"<employee id>\"",
                "get student \"<student id>\"",
                "get course \"<course code>\"",
                "help",
                "exit"));
    }
}
