import java.util.Objects;

class ParkedVehicle {
    private final String registrationNumber;

    ParkedVehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "ParkedVehicle{registration='" + registrationNumber + "'}";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ParkedVehicle other)) return false;
        return registrationNumber.equals(other.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }
}

public class OverridingDemo {
    public static void main(String[] args) {
        ParkedVehicle first = new ParkedVehicle("TN01AB1234");
        ParkedVehicle second = new ParkedVehicle("TN01AB1234");
        System.out.println(first);
        System.out.println("Objects equal: " + first.equals(second));
        System.out.println("Same hash code: " + (first.hashCode() == second.hashCode()));
    }
}