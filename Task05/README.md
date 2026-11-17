Task 5 – Static Members and Object Life Cycle

A ticket sequence belongs to the class when IDs must be unique across all facility instances, so use a static counter (or a central ticket-ID service). Occupancy belongs to each facility and must remain an instance field. Static methods are suitable for ID generation when synchronised appropriately; static blocks are useful only for class-wide initialisation.

After a vehicle exits, remove the active ticket and release references to it. The ticket becomes eligible for garbage collection when no reachable references remain; collection timing is not guaranteed. If the counter were an instance field, two facilities could issue duplicate IDs. One static counter uses O(1) storage for k facilities and n tickets; k instance counters use O(k) storage.