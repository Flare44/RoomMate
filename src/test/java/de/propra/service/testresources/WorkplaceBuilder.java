package de.propra.service.testresources;

import de.propra.domain.Equipment;
import de.propra.domain.Room;
import de.propra.domain.TimeSpan;
import de.propra.domain.Workplace;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkplaceBuilder {
    private Long id = null;
    private Room room = null;
    private List<Equipment> equipment = new ArrayList<>();
    private List<TimeSpan> timeSpans = new ArrayList<>();

    public static WorkplaceBuilder init() {
        return new WorkplaceBuilder();
    }

    public WorkplaceBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public WorkplaceBuilder withId(Integer id) {
        this.id = Long.valueOf(id);
        return this;
    }

    public WorkplaceBuilder withRoom(Room room) {
        this.room = room;
        return this;
    }

    public WorkplaceBuilder withRoom(Long roomId, String roomName, Long workplaces) {
        this.room = new Room(roomId, roomName, workplaces);
        return this;
    }

    public WorkplaceBuilder withRoom(Integer roomId, String roomName, Integer workplaces) {
        this.room = new Room(Long.valueOf(roomId), roomName, Long.valueOf(workplaces));
        return this;
    }

    public WorkplaceBuilder withEquipment(List<Equipment> equipment) {
        this.equipment.addAll(equipment);
        return this;
    }

    public WorkplaceBuilder withEquipment(Equipment... equipment) {
        this.equipment.addAll(Arrays.asList(equipment));
        return this;
    }

    public WorkplaceBuilder withBookedTimeSpans(List<TimeSpan> timeSpans) {
        this.timeSpans.addAll(timeSpans);
        return this;
    }

    public WorkplaceBuilder withBookedTimeSpans(TimeSpan... timeSpans) {
        this.timeSpans.addAll(Arrays.asList(timeSpans));
        return this;
    }

    public WorkplaceBuilder withBookedTimeSpan(TimeSpan timeSpan) {
        this.timeSpans.add(timeSpan);
        return this;
    }

    public WorkplaceBuilder withBookedTimeSpan(LocalDateTime startTime, LocalDateTime endTime) {
        this.timeSpans.add(new TimeSpan(startTime, endTime));
        return this;
    }

    public Workplace build() {
        if(id == null || room == null) {
            throw new IllegalArgumentException("Must contain Workplace-ID and Room");
        }

        return new Workplace(this.id, this.room, this.equipment, this.timeSpans);
    }
}
