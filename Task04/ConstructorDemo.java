class ParkingTicket {
    private static int nextTicketNumber = 1000;
    private final int ticketNumber;
    private final String registrationNumber;
    private final String vehicleType;

    ParkingTicket(String registrationNumber, String vehicleType) {
        this(++nextTicketNumber, registrationNumber, vehicleType);
    }

    private ParkingTicket(int ticketNumber, String registrationNumber, String vehicleType) {
        this.ticketNumber = ticketNumber;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
    }

    ParkingTicket(ParkingTicket other) {
        this(other.ticketNumber, other.registrationNumber, other.vehicleType);
    }

    void display() {
        System.out.println(ticketNumber + " | " + registrationNumber + " | " + vehicleType);
    }
}

public class ConstructorDemo {
    public static void main(String[] args) {
        ParkingTicket original = new ParkingTicket("TN01AB1234", "Car");
        ParkingTicket copied = new ParkingTicket(original);
        original.display();
        copied.display();
    }
}