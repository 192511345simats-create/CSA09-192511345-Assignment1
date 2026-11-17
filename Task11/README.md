Task 11 – Implementation, Testing and Defence

The integrated implementation is organised in `src/parking/model`, `src/parking/service` and `src/parking/app`. The test plan covers normal entry/exit, the last free slot, zero-minute parking, exact hourly boundaries, invalid registration, duplicate active registration, invalid slot coordinates and daily collection totals.

Expected invariants: no slot contains two vehicles; no active registration has two tickets; a valid pass produces zero parking fee; 61 minutes is billed as two started hours; releasing an invalid or already-free slot fails safely. The implementation and test results should be run locally before screenshots are added.

For exit lookup, scanning an array or ArrayList is O(n); a HashMap keyed by registration is average O(1) lookup. All structures use O(n) storage.