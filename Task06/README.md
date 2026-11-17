Task 6 – Inheritance Hierarchy

Use an abstract `Vehicle` superclass with common registration data and an abstract `ratePerHour()` method. `TwoWheeler` and `Car` extend `Vehicle`; `ElectricCar` extends `Car` and implements `Chargeable`. This is hierarchical inheritance with a further specialised subclass. Constructors call `super(regNo)` first, then initialise subclass state.

UML (text form): `Vehicle` is the parent of `TwoWheeler` and `Car`; `Car` is the parent of `ElectricCar`; `ElectricCar` implements `Chargeable`.

Composition alternative: a `Vehicle` could contain a `VehicleType` strategy holding its rate. Composition makes adding types more flexible without growing a subclass tree. Both approaches calculate a fee in O(1); object storage is O(1) per vehicle, with composition adding a referenced strategy object if not shared.