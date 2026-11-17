import java.util.Arrays;

public class DataRepresentationDemo {
    public static void main(String[] args) {
        int floors = 3;
        int[] slotsPerFloor = {4, 3, 5};
        boolean[][] occupied = new boolean[floors][];

        for (int floor = 0; floor < floors; floor++) {
            occupied[floor] = new boolean[slotsPerFloor[floor]];
        }

        occupied[1][2] = true;
        int totalSlots = Arrays.stream(slotsPerFloor).sum();
        int occupiedCount = 0;

        for (boolean[] floor : occupied) {
            for (boolean slot : floor) {
                if (slot) occupiedCount++;
            }
        }

        double occupancyRate = totalSlots == 0 ? 0.0
                : (double) occupiedCount / totalSlots * 100.0;

        System.out.println("Slots per floor: " + Arrays.toString(slotsPerFloor));
        System.out.println("Total slots: " + totalSlots);
        System.out.println("Occupied slots: " + occupiedCount);
        System.out.printf("Occupancy: %.2f%%%n", occupancyRate);
    }
}