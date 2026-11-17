Task 7 – Method Overriding

Subclasses specialise `ratePerHour()` with `@Override`; the inherited `calculateFee()` uses dynamic dispatch to obtain the correct rate. `final` is appropriate for immutable registration numbers and ticket IDs; avoid making extension points final. Override `equals()` and `hashCode()` consistently using the registration number if the business rule defines registration as identity.

A switch-based fee method also takes O(1) time for a fixed number of categories, but adding a type requires editing the central switch. With overriding, a new subtype can provide its own rate while existing tested classes remain unchanged.