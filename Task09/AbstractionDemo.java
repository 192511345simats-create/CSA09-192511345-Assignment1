abstract class ParkingPayment {
    abstract double calculateFee(int hours);

    void printReceipt(String registration, int hours) {
        System.out.printf("%s | %d hours | Rs %.2f%n",
                registration, hours, calculateFee(hours));
    }
}

interface DiscountEligible {
    double applyDiscount(double amount);
}

class VisitorPayment extends ParkingPayment {
    @Override
    double calculateFee(int hours) {
        return Math.max(1, hours) * 30.0;
    }
}

class StudentPayment extends ParkingPayment implements DiscountEligible {
    @Override
    double calculateFee(int hours) {
        return applyDiscount(Math.max(1, hours) * 30.0);
    }

    @Override
    public double applyDiscount(double amount) {
        return amount * 0.5;
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        ParkingPayment visitor = new VisitorPayment();
        ParkingPayment student = new StudentPayment();
        visitor.printReceipt("TN01AA1111", 2);
        student.printReceipt("TN02BB2222", 2);
    }
}