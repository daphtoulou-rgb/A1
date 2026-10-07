public class Sailboat extends WaterVehicle {
    public Sailboat() {
        super();
        System.out.println("Sailboat Constructor called.");
    }

    public Sailboat(String name) {
        super(name);
        System.out.println("Sailboat Constructor with name called.");
    }

    @Override
    public void move() {
        System.out.println("Whoosh! Sailing with the wind.");
    }

    @Override
    public String getVehicleType() {
        return "Sailboat";
    }
    
}
