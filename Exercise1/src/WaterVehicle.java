public class WaterVehicle extends Vehicle {
    public static int numberOfWaterVehicles = 0;

    public WaterVehicle() {
        super();
        numberOfWaterVehicles++;
        System.out.println("WaterVehicle Constructor called.");
    }

    public WaterVehicle(String name) {
        super(name);
        numberOfWaterVehicles++;
        System.out.println("WaterVehicle Constructor with name called.");
    }

    public void move() {
        System.out.println("Floating on water.");
    }

    public String getVehicleType() {
        return "Water Vehicle";
    }

    public static int getNumberOfWaterVehicles() {
        return numberOfWaterVehicles;
    }
}


