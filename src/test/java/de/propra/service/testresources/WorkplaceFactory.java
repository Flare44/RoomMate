package de.propra.service.testresources;

import de.propra.domain.Equipment;
import de.propra.domain.Room;
import de.propra.domain.Workplace;

import java.time.LocalDateTime;
import java.util.List;

import static de.propra.service.testresources.WorkplaceBuilder.init;

public class WorkplaceFactory {
    private static final List<Room> rooms = List.of(
            new Room(1L, "Empfang", 2L),
            new Room(2L, "HR", 3L),
            new Room(3L, "Sales", 8L),
            new Room(4L, "Marketing", 5L),
            new Room(5L, "Entwicklung", 3L)
    );
    public static final List<Workplace> WORKPLACES_NORMAL = List.of(
            init()
                    .withId(1)
                    .withRoom(rooms.get(0))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_USB_C)
                    .build(),
            init()
                    .withId(2)
                    .withRoom(rooms.get(0))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_HDMI)
                    .withBookedTimeSpan(
                            LocalDateTime.of(2023, 10, 25, 8, 0),
                            LocalDateTime.of(2023, 10, 25, 17, 30)
                    )
                    .build(),
            init()
                    .withId(3)
                    .withRoom(rooms.get(1))
                    .build(),
            init()
                    .withId(4)
                    .withRoom(rooms.get(1))
                    .withEquipment(Equipment.DESK_HEIGHT_ADJUSTABLE)
                    .build(),
            init()
                    .withId(5)
                    .withRoom(rooms.get(2))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_HDMI, Equipment.DESK_HEIGHT_ADJUSTABLE)
                    .withBookedTimeSpan(
                            LocalDateTime.of(2023, 12, 5, 7, 30),
                            LocalDateTime.of(2023, 12, 5, 16, 45)
                    )
                    .build(),
            init()
                    .withId(6)
                    .withRoom(rooms.get(2))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_DP)
                    .build(),
            init()
                    .withId(7)
                    .withRoom(rooms.get(2))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_HDMI, Equipment.DESK_HEIGHT_ADJUSTABLE)
                    .build(),
            init()
                    .withId(8)
                    .withRoom(rooms.get(2))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_DP)
                    .build(),
            init()
                    .withId(9)
                    .withRoom(rooms.get(3))
                    .withBookedTimeSpan(
                            LocalDateTime.of(2023, 11, 26, 10, 25),
                            LocalDateTime.of(2023, 11, 26, 21, 20)
                    )
                    .build(),
            init()
                    .withId(10)
                    .withRoom(rooms.get(4))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_DP, Equipment.DESK_HEIGHT_ADJUSTABLE)
                    .build()
    );

    public static final List<Workplace> WORKPLACES_MULTIPLE_COLLISIONS = List.of(
            init()
                    .withId(1)
                    .withRoom(rooms.get(0))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_USB_C)
                    .withBookedTimeSpan(
                            LocalDateTime.of(2023, 10, 25, 8, 0),
                            LocalDateTime.of(2023, 10, 25, 17, 30)
                    )
                    .build(),
            init()
                    .withId(2)
                    .withRoom(rooms.get(0))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_HDMI)
                    .withBookedTimeSpan(
                            LocalDateTime.of(2023, 10, 25, 8, 0),
                            LocalDateTime.of(2023, 10, 25, 17, 30)
                    )
                    .build(),
            init()
                    .withId(3)
                    .withRoom(rooms.get(1))
                    .withBookedTimeSpan(
                            LocalDateTime.of(2023, 10, 25, 8, 0),
                            LocalDateTime.of(2023, 10, 25, 17, 30)
                    )
                    .build(),
            init()
                    .withId(4)
                    .withRoom(rooms.get(1))
                    .withEquipment(Equipment.DESK_HEIGHT_ADJUSTABLE)
                    .build(),
            init()
                    .withId(5)
                    .withRoom(rooms.get(2))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_HDMI, Equipment.DESK_HEIGHT_ADJUSTABLE)
                    .withBookedTimeSpan(
                            LocalDateTime.of(2023, 12, 5, 7, 30),
                            LocalDateTime.of(2023, 12, 5, 16, 45)
                    )
                    .build(),
            init()
                    .withId(6)
                    .withRoom(rooms.get(2))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_DP)
                    .build(),
            init()
                    .withId(7)
                    .withRoom(rooms.get(2))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_HDMI, Equipment.DESK_HEIGHT_ADJUSTABLE)
                    .withBookedTimeSpan(
                            LocalDateTime.of(2023, 10, 25, 8, 0),
                            LocalDateTime.of(2023, 10, 25, 17, 30)
                    )
                    .build(),
            init()
                    .withId(8)
                    .withRoom(rooms.get(2))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_DP)
                    .withBookedTimeSpan(
                            LocalDateTime.of(2023, 10, 25, 8, 0),
                            LocalDateTime.of(2023, 10, 25, 17, 30)
                    )
                    .build(),
            init()
                    .withId(9)
                    .withRoom(rooms.get(3))
                    .withBookedTimeSpan(
                            LocalDateTime.of(2023, 11, 26, 10, 25),
                            LocalDateTime.of(2023, 11, 26, 21, 20)
                    )
                    .build(),
            init()
                    .withId(9)
                    .withRoom(rooms.get(4))
                    .withEquipment(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_DP, Equipment.DESK_HEIGHT_ADJUSTABLE)
                    .build()
    );
}
