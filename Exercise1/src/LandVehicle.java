public class LandVehicle extends Vehicle {
    public static int numberOfLandVehicles = 0;
    
    public LandVehicle() {
        super();
        numberOfLandVehicles++;
        System.out.println("LandVehicle Constructor called.");
    }

    public LandVehicle(String name) {
        super(name);
        numberOfLandVehicles++;
        System.out.println("LandVehicle Constructor with name called.");
    }

    public void move() {
        System.out.println("Rolling on land.");
    }

    @Override
    public String getVehicleType() {
        return "Land Vehicle";
    }

    public static int getNumberOfLandVehicles() {
        return numberOfLandVehicles;
    }
}

