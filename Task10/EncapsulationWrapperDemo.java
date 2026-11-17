class ParkingAccount {
    private final String studentId;
    private int freeHours;

    ParkingAccount(String studentId, int freeHours) {
        this.studentId = studentId;
        setFreeHours(freeHours);
    }

    public String getStudentId() { return studentId; }
    public int getFreeHours() { return freeHours; }

    public void setFreeHours(int freeHours) {
        if (freeHours < 0) throw new IllegalArgumentException("Hours cannot be negative");
        this.freeHours = freeHours;
    }
}

public class EncapsulationWrapperDemo {
    public static void main(String[] args) {
        ParkingAccount account = new ParkingAccount("STU1925", 2);
        Integer boxedHours = account.getFreeHours();
        int unboxedHours = boxedHours;

        System.out.println("Student: " + account.getStudentId());
        System.out.println("Free hours (wrapper): " + boxedHours);
        System.out.println("Free hours (primitive): " + unboxedHours);
    }
}