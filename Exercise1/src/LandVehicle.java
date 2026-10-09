/**
 * Student 1: Daphnee Toulou (300501881)
 * Student 2: Ngozi Onyechere (300485967)
 */

/**
 * LandVehicle represents a vehicle that operates on land.
 * This class extends the Vehicle class and provides implementations for move() and getVehicleType().
 */
public class LandVehicle extends Vehicle {
    
    /**
     * Static variable to keep track of the total number of land vehicles created.
     */
    private static int numberOfLandVehicles = 0;
    
    /**
     * Default constructor for LandVehicle.
     * Calls the default constructor of Vehicle (aka the superclass). 
     * Increments the total number of land vehicles by 1.
     * Prints "LandVehicle Constructor called".
     */
    public LandVehicle() {
        super();
        numberOfLandVehicles++;
        System.out.println("LandVehicle Constructor called");
    }

    /**
     * Parameterized constructor for LandVehicle.
     * Accepts a String parameter for the land vehicle's name.
     * Calls the parameterized constructor of Vehicle (aka the superclass) with the provided value. 
     * Increments the total number of land vehicles by 1.
     * Prints "LandVehicle Constructor with name called".
     *
     * @param name The name of the land vehicle.
     */
    public LandVehicle(String name) {
        super(name);
        numberOfLandVehicles++;
        System.out.println("LandVehicle Constructor with name called");
    }

    /**
     * Implementation of the move() method from the Movable interface.
     * Prints "Rolling on land" to indicate that the land vehicle is moving.
     */
    public void move() {
        System.out.println("Rolling on land");
    }

    /**
     * Implementation of the getVehicleType() method from the Vehicle class.
     * Returns the type of the vehicle as a String.
     *
     * @return the vehicle type as a String, "Land Vehicle."
     */
    @Override
    public String getVehicleType() {
        return "Land Vehicle";
    }

    /**
     * Static method to get the total number of land vehicles created.
     *
     * @return The total number of land vehicles created.
     */
    public static int getNumberOfLandVehicles() {
        return numberOfLandVehicles;
    }
}

