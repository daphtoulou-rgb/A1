/**
 * Student 1: Daphnee Toulou (300501881)
 * Student 2: Ngozi Onyechere (300485967)
 */

/**
 * Car represents a specific type of LandVehicle.
 * This class extends the LandVehicle class and provides implementations for move() and getVehicleType().
 */
public class Car extends LandVehicle {
    
    /**
     * Default constructor for Car.
     * Calls the default constructor of LandVehicle (aka the superclass).
     * Prints "Car Constructor called".
     */
    public Car() {
        super();
        System.out.println("Car Constructor called");
    }

    /**
     * Parameterized constructor for Car.
     * Accepts a String parameter for the car's name.
     * Calls the parameterized constructor of LandVehicle (aka the superclass) with the provided value.
     * Prints "Car Constructor with name called".
     *
     * @param name The name of the car.
     */
    public Car(String name) {
        super(name);
        System.out.println("Car Constructor with name called");
    }

    /**
     * Implementation of the move() method from the Movable interface.
     * Prints "Vroom! Driving on the road" to indicate that the car is moving.
     */
    @Override
    public void move() {
        System.out.println("Vroom! Driving on the road");
    }

    /**
     * Implementation of the getVehicleType() method from the Vehicle class.
     * Returns the type of the vehicle as a String.
     *
     * @return the vehicle type as a String, "Car"
     */
    @Override
    public String getVehicleType() {
        return "Car";
    }
    
}
