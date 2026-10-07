public class Car extends LandVehicle {
    public Car() {
        super();
        System.out.println("Car Constructor called.");
    }

    public Car(String name) {
        super(name);
        System.out.println("Car Constructor with name called.");
    }

    @Override
    public void move() {
        System.out.println("Vroom! Driving on the road.");
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
    
}
