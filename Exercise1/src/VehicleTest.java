/**
 * Student 1: Daphnee Toulou (300501881)
 * Student 2: Ngozi Onyechere (300485967)
 */

/**
 * Answer to Step 7f:
 * Vehicle objects cannot be created directly because Vehicle is an abstract class. 
 * List<Vehicle> can however, hold objects of its subclasses because polymorphism allows them to be treated as Vehicle objects.
*/

import java.util.ArrayList;
import java.util.List;

/**
 * VehicleTest serves as a test class to demonstrate polymorphism, constructor chaining, and class variables in the context of a vehicle hierarchy.
 */
public class VehicleTest {
    /**
     * The main method serves as the entry point for the program.
     * It creates instances of LandVehicle, WaterVehicle, Car, and Sailboat, demonstrating polymorphism and constructor chaining.
     * It also prints out the total number of vehicles created, as well as the total number of land and water vehicles.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        // Create lists for each concrete type of vehicle
        ArrayList<LandVehicle> landVehicles = new ArrayList<>();
        ArrayList<WaterVehicle> waterVehicles = new ArrayList<>();
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Sailboat> sailboats = new ArrayList<>();

        // Create four instances of each concrete type of vehicle, two with the default constructor and two with the parameterized constructor
        landVehicles.add(new LandVehicle());
        landVehicles.add(new LandVehicle());
        landVehicles.add(new LandVehicle("Tractor"));
        landVehicles.add(new LandVehicle("Bus"));

        waterVehicles.add(new WaterVehicle());
        waterVehicles.add(new WaterVehicle());
        waterVehicles.add(new WaterVehicle("Ferry"));
        waterVehicles.add(new WaterVehicle("Canoe"));

        cars.add(new Car());
        cars.add(new Car());
        cars.add(new Car("Civic"));
        cars.add(new Car("Corolla"));
        
        sailboats.add(new Sailboat());
        sailboats.add(new Sailboat());
        sailboats.add(new Sailboat("Bluenose"));
        sailboats.add(new Sailboat("Laser"));

        // Iterate over each list and calls the move() method on every object.
        System.out.println("\nLand vehicles moving:");
        for (LandVehicle vehicle : landVehicles) {
            vehicle.move();
        }

        System.out.println("\nWater vehicles moving:");
        for (WaterVehicle vehicle : waterVehicles) {
            vehicle.move();
        }

        System.out.println("\nCars moving:");
        for (Car car : cars) {
            car.move();
        }

        System.out.println("\nSailboats moving:");
        for (Sailboat sailboat : sailboats) {
            sailboat.move();
        }
        
        System.out.println("\nFleet:");
        // Add all vehicle objects to a single list called fleet.
        List<Vehicle> fleet = new ArrayList<>();
        fleet.addAll(landVehicles);
        fleet.addAll(waterVehicles);
        fleet.addAll(cars);
        fleet.addAll(sailboats);

        //  Iterate over fleet and call describe() on every object.
        for (Vehicle vehicle : fleet) {
            vehicle.describe();
        }

        // Print the total number of created instances for each class by calling their respective static methods.
        System.out.println("\nTotal number of vehicles: " + Vehicle.getNumberOfVehicles());
        System.out.println("Total number of land vehicles: " + LandVehicle.getNumberOfLandVehicles());
        System.out.println("Total number of water vehicles: " + WaterVehicle.getNumberOfWaterVehicles());
    }
}
