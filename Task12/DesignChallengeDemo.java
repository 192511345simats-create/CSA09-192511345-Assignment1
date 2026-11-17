import java.time.LocalDateTime;

/** Day-4 extension: new categories and pluggable pricing policies. */
public class DesignChallengeDemo {
    interface PricingRule {
        double parkingFee(long minutes, LocalDateTime entry, double baseHourlyRate);
    }
    static final class StandardHourlyRule implements PricingRule {
        public double parkingFee(long minutes, LocalDateTime entry, double rate) {
            if (minutes < 0) throw new IllegalArgumentException("Negative duration");
            return ((minutes + 59) / 60) * rate;
        }
    }
    static final class FlatFeeRule implements PricingRule {
        private final double amount;
        FlatFeeRule(double amount) {
            if (amount < 0) throw new IllegalArgumentException("Negative fee");
            this.amount = amount;
        }
        public double parkingFee(long minutes, LocalDateTime entry, double rate) {
            if (minutes < 0) throw new IllegalArgumentException("Negative duration");
            return amount;
        }
    }
    static final class DeliveryVanRule implements PricingRule {
        public double parkingFee(long minutes, LocalDateTime entry, double carRate) {
            if (minutes < 0) throw new IllegalArgumentException("Negative duration");
            LocalDateTime exit = entry.plusMinutes(minutes);
            // Apply the double rate to each billable hour if the stay overlaps 09:00–17:00.
            boolean daytime = entry.toLocalDate().equals(exit.toLocalDate())
                    && !exit.toLocalTime().isBefore(java.time.LocalTime.of(9, 0))
                    && !entry.toLocalTime().isAfter(java.time.LocalTime.of(17, 0));
            double rate = daytime ? carRate * 2 : carRate;
            return ((minutes + 59) / 60) * rate;
        }
    }
    static final class DayPass {
        private final LocalDateTime validFrom;
        DayPass(LocalDateTime validFrom) { this.validFrom = validFrom; }
        boolean isValidAt(LocalDateTime time) {
            return !time.isBefore(validFrom) && time.isBefore(validFrom.plusDays(1));
        }
    }
    static final class EBike {
        private final PricingRule pricing = new FlatFeeRule(15.0);
        double fee(long minutes, LocalDateTime entry) {
            return pricing.parkingFee(minutes, entry, 0);
        }
    }
    static final class DeliveryVan {
        private final PricingRule pricing = new DeliveryVanRule();
        double fee(long minutes, LocalDateTime entry) {
            return pricing.parkingFee(minutes, entry, 30.0);
        }
    }

    public static void main(String[] args) {
        LocalDateTime morning = LocalDateTime.of(2026, 11, 17, 10, 0);
        EBike bike = new EBike();
        DeliveryVan van = new DeliveryVan();
        DayPass pass = new DayPass(morning);
        System.out.println("E-bike flat fee: Rs " + bike.fee(45, morning));
        System.out.println("Delivery van daytime fee (1 hour): Rs " + van.fee(60, morning));
        System.out.println("Visitor day pass valid now: " + pass.isValidAt(morning));
        System.out.println("Visitor day pass valid tomorrow: " + pass.isValidAt(morning.plusDays(1)));
    }
}