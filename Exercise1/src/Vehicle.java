/**
 * Student 1: Daphnee Toulou (300501881)
 * Student 2: Ngozi Onyechere (300485967)
 */

/**
 * Vehicle serves as an abstract base class for all types of vehicles.
 * Implements the Movable interface.
 * Keeps track of the total number of vehicles created.
 */
abstract class Vehicle implements Movable {
    
    /**
     * Tracks the total number of vehicles created.
     */
    private static int numberOfVehicles = 0;
    
    /**
     * Stores the name of the vehicle.
     */
    private String name;

    /**
     * Default constructor for Vehicle.
     * Increments the total number of vehicles by 1.
     * Prints "Vehicle Constructor called".
     * Initializes the name field to "Unnamed Vehicle."
     */
    public Vehicle(){
        numberOfVehicles++;
        System.out.println("Vehicle Constructor called");
        this.name = "Unnamed Vehicle";
    }

    /**
     * Parameterized constructor for Vehicle.
     * Accepts a String parameter for the vehicle's name
     * Increments the total number of vehicles by 1.
     * Prints "Vehicle Constructor with name called".
     * Initializes the name field with the provided value.
     *
     * @param name The name of the vehicle.
     */
    public Vehicle(String name) {
        numberOfVehicles++;
        System.out.println("Vehicle Constructor with name called");
        this.name = name;
    }

    /**
     * Abstract method to get the type of the vehicle.
     * Must be implemented by subclasses.
     *
     * @return A String representing the type of the vehicle.
     */
    public abstract String getVehicleType();

    /**
     * Getter for the name of the vehicle because name is private and cannot be accessed directly from outside the class.
     * 
     * @return The name of the vehicle.
     */
    public String getName() {
        return name;
    }

    /**
     * Prints the name of the vehicle followed by its type, by calling getVehicleType(). 
     */
    public void describe() {
        System.out.println(name + " is a " + getVehicleType());
    }

    /**
     * Static method to keep track of the total number of vehicles created.
     * 
     * @return The total number of vehicles created.
     */
    public static int getNumberOfVehicles() {
        return numberOfVehicles;
    }
}