import java.util.ArrayList;
import java.util.List;

public class VehicleTest {
    public static void main(String[] args) {
        ArrayList<LandVehicle> landVehicles = new ArrayList<>();
        ArrayList<WaterVehicle> waterVehicles = new ArrayList<>();
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Sailboat> sailboats = new ArrayList<>();

        // Create instances of LandVehicle and WaterVehicle
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

        for (LandVehicle vehicle : landVehicles) {
            vehicle.move();
        }

        for (WaterVehicle vehicle : waterVehicles) {
            vehicle.move();
        }

        for (Car car : cars) {
            car.move();
        }

        for (Sailboat sailboat : sailboats) {
            sailboat.move();
        }
            
        List<Vehicle> fleet = new ArrayList<>();
        fleet.addAll(landVehicles);
        fleet.addAll(waterVehicles);
        fleet.addAll(cars);
        fleet.addAll(sailboats);

        for (Vehicle vehicle : fleet) {
            vehicle.describe();
        }

        System.out.println("Total number of vehicles: " + Vehicle.getNumberOfVehicles());
        System.out.println("Total number of land vehicles: " + LandVehicle.getNumberOfLandVehicles());
        System.out.println("Total number of water vehicles: " + WaterVehicle.getNumberOfWaterVehicles());
    }
}
