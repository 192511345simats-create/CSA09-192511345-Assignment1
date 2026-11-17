Task 10 – Packages and Encapsulation

Suggested package structure: `parking.model` for Vehicle, ParkingSlot and ParkingTicket; `parking.service` for ParkingFacility and pricing rules; `parking.app` for the main program. Keep fields private, expose public methods only where other packages need them, and use protected members sparingly for subclass extension. Validate registration numbers, slot coordinates, durations and fees at object boundaries.

Use wrapper types when nullability or generic collections require them. Do not compare `Integer` values with `==`; use primitive values or `.equals()`. Public fields would allow callers to bypass validation and corrupt the facility state. Validation is O(1) per field update, and centralising it avoids duplicated checks throughout the application.