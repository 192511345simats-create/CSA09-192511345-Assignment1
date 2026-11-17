Task 2 – Data Representation

Use `String` for registration number, `int` for floor and slot indices, `long` for ticket number, `LocalDateTime` for entry/exit timestamps, `long` for duration in minutes and `double` or preferably `BigDecimal` for currency. Use a jagged `String[][]` or `ParkingSlot[][]` because floors may have different capacities.

A jagged array has O(total slots) storage and direct O(1) access when floor and slot are known. Searching for a free slot is O(total slots). A flat array can still represent unequal floors only with offset metadata; otherwise the formula `floor * slotsPerFloor + slot` assumes equal floor sizes. `ArrayList<String[]>` also supports unequal floor sizes but has similar indexed access and slightly more collection overhead.

Avoid integer truncation: `130 / 60` is 2, not 3. For every started hour, calculate `(minutes + 59) / 60` using integer arithmetic after validating non-negative minutes. Use `Scanner` for interactive console input.