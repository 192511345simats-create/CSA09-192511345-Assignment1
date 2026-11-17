Task 8 – Polymorphism

Compile-time polymorphism is method overloading, such as `calculateFee(int minutes)` and `calculateFee(LocalDateTime in, LocalDateTime out)`. Runtime polymorphism occurs when a `Vehicle` reference points to a `Car` object and the overridden method is selected at runtime. A `Vehicle[]` can hold different subclasses through upcasting.

For `Vehicle v = new Car("TN02B2")`, the declared type is `Vehicle`, actual type is `Car`, and the overridden `ratePerHour()` in `Car` executes during fee calculation. This avoids repeated type checks. The completed `FeeDemo.java` is included.