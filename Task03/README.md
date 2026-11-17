Task 3 – Control Logic

## Allocate pseudocode
1. Validate the vehicle number.
2. For each floor, scan each slot.
3. On the first free compatible slot, store the vehicle number and return its coordinates.
4. If no slot is free, return null.

## Fee pseudocode
1. Reject negative minutes or rates.
2. If a valid pass applies, return zero.
3. Compute billable hours as `(minutes + 59) / 60`.
4. Multiply by the hourly rate and apply any daily cap.

`return` ends the search when a slot is found; `continue` skips unsuitable slots; `break` can stop a loop when no further candidate is possible. The completed Java program is `ParkingFacility.java`.