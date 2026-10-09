/**
 * Student 1: Daphnee Toulou (300501881)
 * Student 2: Ngozi Onyechere (300485967)
 */

/**
 * Sailboat represents a specific type of WaterVehicle.
 * This class extends the WaterVehicle class and provides specific implementations for move() and getVehicleType().
 */
public class Sailboat extends WaterVehicle {
    
    /**
     * Default constructor for Sailboat.
     * Calls the default constructor of WaterVehicle (aka the superclass).
     * Prints "Sailboat Constructor called".
     */
    public Sailboat() {
        super();
        System.out.println("Sailboat Constructor called");
    }

    /**
     * Parameterized constructor for Sailboat.
     * Accepts a String parameter for the sailboat's name.
     * Calls the parameterized constructor of WaterVehicle (aka the superclass) with the provided value.
     * Prints "Sailboat Constructor with name called".
     *
     * @param name The name of the sailboat.
     */
    public Sailboat(String name) {
        super(name);
        System.out.println("Sailboat Constructor with name called");
    }

    /**
     * Implementation of the move() method from the Movable interface.
     * Prints "Whoosh! Sailing with the wind" to indicate that the sailboat is moving.
     */
    @Override
    public void move() {
        System.out.println("Whoosh! Sailing with the wind");
    }

    /**
     * Implementation of the getVehicleType() method from the Vehicle class.
     * Returns the type of the vehicle as a String.
     *
     * @return the vehicle type as a String, "Sailboat"
     */
    @Override
    public String getVehicleType() {
        return "Sailboat";
    }
    
}
