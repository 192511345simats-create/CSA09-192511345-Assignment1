Task 12 – Design Challenge Adaptation

Decision: MODIFY the design while retaining the core `Vehicle` abstraction and jagged floor representation. Add `EBike` with a flat-fee policy and `DeliveryVan` with a time-window pricing rule (double the car rate from 9 AM through 5 PM). Add a `DayPass` capability for visitors and a `PricingRule` abstraction so time-dependent pricing is not scattered across vehicle classes.

A jagged array supports five floors with different slot counts without changing allocation logic. Existing vehicle subclasses and tested slot operations should remain unchanged; new categories are added as new implementations. The time-window rule must define its boundary precisely and use local date-time values. Test both sides of 9 AM and 5 PM, flat e-bike fees, one-day-pass validity and mixed floor capacities.

Trade-off: the strategy-based pricing design adds a small amount of indirection but reduces changes to existing classes when future pricing rules are introduced.