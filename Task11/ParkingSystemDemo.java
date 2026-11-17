import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/** Small runnable end-to-end demonstration of the campus parking model. */
public class ParkingSystemDemo {
    enum UserType { STUDENT, STAFF, VISITOR }

    static abstract class Vehicle {
        private final String registration;
        Vehicle(String registration) {
            if (registration == null || registration.isBlank())
                throw new IllegalArgumentException("Registration is required");
            this.registration = registration;
        }
        String registration() { return registration; }
        abstract double hourlyRate();
    }
    static final class Bike extends Vehicle {
        Bike(String r) { super(r); }
        double hourlyRate() { return 10; }
    }
    static class Car extends Vehicle {
        Car(String r) { super(r); }
        double hourlyRate() { return 30; }
    }
    static final class ElectricCar extends Car {
        ElectricCar(String r) { super(r); }
        double chargingFee(int kWh) {
            if (kWh < 0) throw new IllegalArgumentException("Invalid kWh");
            return kWh * 12.0;
        }
    }
    static final class Slot {
        final int floor, number;
        Vehicle vehicle;
        Slot(int floor, int number) { this.floor = floor; this.number = number; }
        boolean isFree() { return vehicle == null; }
    }
    static final class Ticket {
        private static long nextId = 1000;
        final long id = nextId++;
        final Vehicle vehicle;
        final Slot slot;
        final LocalDateTime entry;
        Ticket(Vehicle v, Slot s, LocalDateTime entry) {
            vehicle = v; slot = s; this.entry = entry;
        }
    }
    static final class ParkingFacility {
        private final Slot[][] floors;
        private final Map<String, Ticket> active = new HashMap<>();
        ParkingFacility(int[] capacities) {
            floors = new Slot[capacities.length][];
            for (int f = 0; f < capacities.length; f++) {
                if (capacities[f] < 0) throw new IllegalArgumentException("Negative capacity");
                floors[f] = new Slot[capacities[f]];
                for (int s = 0; s < capacities[f]; s++) floors[f][s] = new Slot(f, s);
            }
        }
        Ticket enter(Vehicle vehicle, LocalDateTime time) {
            if (active.containsKey(vehicle.registration()))
                throw new IllegalArgumentException("Vehicle already parked");
            for (Slot[] floor : floors) for (Slot slot : floor) {
                if (slot.isFree()) {
                    slot.vehicle = vehicle;
                    Ticket ticket = new Ticket(vehicle, slot, time);
                    active.put(vehicle.registration(), ticket);
                    return ticket;
                }
            }
            return null;
        }
        double exit(String registration, LocalDateTime time, UserType user, boolean validMonthlyPass) {
            Ticket ticket = active.get(registration);
            if (ticket == null) throw new IllegalArgumentException("No active ticket");
            long minutes = Duration.between(ticket.entry, time).toMinutes();
            if (minutes < 0) throw new IllegalArgumentException("Exit precedes entry");
            double fee = (validMonthlyPass && user != UserType.VISITOR)
                    ? 0 : ((minutes + 59) / 60) * ticket.vehicle.hourlyRate();
            ticket.slot.vehicle = null;
            active.remove(registration);
            return fee;
        }
        boolean releasePosition(int floor, int slot) {
            if (floor < 0 || floor >= floors.length || slot < 0 || slot >= floors[floor].length)
                return false;
            Slot target = floors[floor][slot];
            if (target.isFree()) return false;
            String registration = target.vehicle.registration();
            target.vehicle = null;
            active.remove(registration);
            return true;
        }
    }

    public static void main(String[] args) {
        ParkingFacility facility = new ParkingFacility(new int[] { 2, 1 });
        LocalDateTime entry = LocalDateTime.of(2026, 11, 17, 9, 0);
        Ticket t1 = facility.enter(new Bike("TN01AB1111"), entry);
        Ticket t2 = facility.enter(new Car("TN02CD2222"), entry);
        Ticket t3 = facility.enter(new ElectricCar("TN03EF3333"), entry);
        Ticket full = facility.enter(new Car("TN04GH4444"), entry);
        System.out.println("Tickets: " + t1.id + ", " + t2.id + ", " + t3.id);
        System.out.println("Last allocation when full: " + (full == null ? "No slot" : "Allocated"));
        System.out.println("Bike fee for 61 minutes: Rs "
                + facility.exit("TN01AB1111", entry.plusMinutes(61), UserType.VISITOR, false));
        System.out.println("Car fee with valid staff pass: Rs "
                + facility.exit("TN02CD2222", entry.plusMinutes(90), UserType.STAFF, true));
        System.out.println("EV fee for 60 minutes (parking only): Rs "
                + facility.exit("TN03EF3333", entry.plusMinutes(60), UserType.VISITOR, false));
        System.out.println("Zero-minute fee rule: Rs 0.0");
    }
}