class Ticket {
    private static int issuedCount = 0;
    private final int ticketId;
    private final String registration;

    Ticket(String registration) {
        this.registration = registration;
        this.ticketId = ++issuedCount;
    }

    static int getIssuedCount() { return issuedCount; }

    void print() {
        System.out.println("Ticket " + ticketId + " for " + registration);
    }
}

public class StaticLifecycleDemo {
    public static void main(String[] args) {
        Ticket first = new Ticket("TN01AA1111");
        Ticket second = new Ticket("TN02BB2222");
        first.print();
        second.print();
        System.out.println("Total tickets issued: " + Ticket.getIssuedCount());
    }
}