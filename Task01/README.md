Task 1 – Problem Analysis and Object Model

## Inputs and outputs
Inputs include vehicle registration number, vehicle category, user category, monthly-pass validity, requested charging-slot requirement and entry/exit time. Outputs include allocated floor/slot, unique ticket number, entry record, exit fee and updated occupancy.

## Candidate classes
- `Vehicle`: registration number and vehicle-specific hourly rate.
- `TwoWheeler`, `Car`, `ElectricCar`: specialised vehicle behaviour.
- `ParkingSlot`: slot number, floor, charging capability and current vehicle.
- `ParkingFacility`: owns floors and allocates/releases slots.
- `ParkingTicket`: unique ticket ID, vehicle, slot and entry time.
- `ParkingUser` / pass information: identifies student, staff or visitor entitlement.
- `PricingRule`: separates fee policy from vehicle data.

Verbs become methods such as `allocate`, `release`, `issueTicket`, `calculateFee` and `validatePass`.

## Business rules and assumptions
A slot can hold at most one vehicle; a vehicle registration cannot have two active tickets; ticket IDs are never reused; only compatible slots are allocated; invalid durations and invalid slot coordinates are rejected; valid student/staff monthly passes waive parking fees. EV charging fees are separate. Times are measured using Java time types.

## Alternative: parallel arrays
A registration search is O(n) in either an object array or parallel arrays. Parallel arrays require every new attribute to be added and kept aligned in multiple arrays; an object groups related state and behaviour, reducing maintenance errors. Both use O(n) total storage; per-vehicle object overhead is implementation-dependent.