class Vehicle {
    protected String registrationNumber;

    Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    void display() {
        System.out.println("Vehicle registration: " + registrationNumber);
    }
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    void display() {
        System.out.println("Two-wheeler: " + registrationNumber);
    }
}

class Car extends Vehicle {
    Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    void display() {
        System.out.println("Car: " + registrationNumber);
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Vehicle vehicle1 = new TwoWheeler("TN01AA1111");
        Vehicle vehicle2 = new Car("TN02BB2222");
        vehicle1.display();
        vehicle2.display();
    }
}