abstract class Vehicle implements Movable {
    private static int numberOfVehicles = 0;
    private String name;

    public Vehicle(){
        numberOfVehicles++;
        System.out.println("Vehicle Constructor called.");
        this.name = "Unnamed Vehicle";
    }

    public Vehicle(String name) {
        numberOfVehicles++;
        System.out.println("Vehicle Constructor with name called.");
        this.name = name;
    }

    public abstract String getVehicleType();

    public String getName() {
        return name;
    }

    public void describe() {
        System.out.println(name + " is a " + getVehicleType());
    }

    public static int getNumberOfVehicles() {
        return numberOfVehicles;
    }
}