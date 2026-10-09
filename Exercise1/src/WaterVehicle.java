/**
 * Student 1: Daphnee Toulou (300501881)
 * Student 2: Ngozi Onyechere (300485967)
 */

/**
 * WaterVehicle represents a specific type of Vehicle that operates on water.
 * This class extends the Vehicle class and provides implementations for move() and getVehicleType().
 */
public class WaterVehicle extends Vehicle {
    
    /**
     * Static variable to keep track of the total number of water vehicles created.
     */
    private static int numberOfWaterVehicles = 0;

    /**
     * Default constructor for WaterVehicle.
     * Calls the default constructor of Vehicle (aka the superclass). 
     * Increments the total number of water vehicles by 1.
     * Prints "WaterVehicle Constructor called".
     */
    public WaterVehicle() {
        super();
        numberOfWaterVehicles++;
        System.out.println("WaterVehicle Constructor called");
    }

    /**
     * Parameterized constructor for WaterVehicle.
     * Accepts a String parameter for the water vehicle's name.
     * Calls the parameterized constructor of Vehicle (aka the superclass) with the provided value. 
     * Increments the total number of water vehicles by 1.
     * Prints "WaterVehicle Constructor with name called".
     *
     * @param name The name of the water vehicle.
     */
    public WaterVehicle(String name) {
        super(name);
        numberOfWaterVehicles++;
        System.out.println("WaterVehicle Constructor with name called");
    }

    /**
     * Implementation of the move() method from the Movable interface.
     * Prints "Floating on water" to indicate that the water vehicle is moving.
     */
    public void move() {
        System.out.println("Floating on water");
    }

    /**
     * Implementation of the getVehicleType() method from the Vehicle class.
     * Returns the type of the vehicle as a String.
     *
     * @return the type of the vehicle as a String, "Water Vehicle."
     */
    @Override
    public String getVehicleType() {
        return "Water Vehicle";
    }

    /**
     * Static method to get the total number of water vehicles created.
     *
     * @return The total number of water vehicles created.
     */
    public static int getNumberOfWaterVehicles() {
        return numberOfWaterVehicles;
    }
}


