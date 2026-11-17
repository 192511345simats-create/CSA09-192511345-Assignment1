import java.util.*;

class Vehicle {
    private final String registrationNumber;
    private final String type;

    Vehicle(String registrationNumber, String type) {
        this.registrationNumber = registrationNumber;
        this.type = type;
    }

    String getRegistrationNumber() { return registrationNumber; }
    String getType() { return type; }
}

class ParkingSlot {
    private final int floor;
    private final int slotNumber;
    private Vehicle parkedVehicle;

    ParkingSlot(int floor, int slotNumber) {
        this.floor = floor;
        this.slotNumber = slotNumber;
    }

    boolean park(Vehicle vehicle) {
        if (parkedVehicle != null) return false;
        parkedVehicle = vehicle;
        return true;
    }

    Vehicle release() {
        Vehicle released = parkedVehicle;
        parkedVehicle = null;
        return released;
    }

    boolean isAvailable() { return parkedVehicle == null; }
    String getLocation() { return "Floor " + floor + ", Slot " + slotNumber; }
}

public class ObjectModelDemo {
    public static void main(String[] args) {
        ParkingSlot slot = new ParkingSlot(1, 4);
        Vehicle car = new Vehicle("TN01AB1234", "Car");
        System.out.println("Slot available: " + slot.isAvailable());
        System.out.println("Vehicle parked: " + slot.park(car));
        System.out.println("Vehicle: " + car.getType() + " " + car.getRegistrationNumber());
        System.out.println("Location: " + slot.getLocation());
        System.out.println("Released: " + slot.release().getRegistrationNumber());
    }
}