package de.propra.domain;

import java.util.ArrayList;
import java.util.List;


public class Workplace {
    private final Long id;
    private final Room assignedRoom;
    private final List<Equipment> equipment = new ArrayList<>();
    private final List<TimeSpan> bookedTimeSpans = new ArrayList<>();

    public Workplace(Long id, Room room) {
        this.id = id;
        this.assignedRoom = room;
    }

    public Workplace(Long id, Room room, List<Equipment> equipment) {
        this.id = id;
        this.assignedRoom = room;
        this.equipment.addAll(equipment);
    }


    public Workplace(Long id, Room room, List<Equipment> equipment, List<TimeSpan> bookedTimeSpans) {
        this.id = id;
        this.assignedRoom = room;
        this.equipment.addAll(equipment);
        this.bookedTimeSpans.addAll(bookedTimeSpans);
    }





    public Long getId() {
        return id;
    }

    public Room getAssignedRoom() {
        return assignedRoom;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public List<TimeSpan> getBookedTimeSpans() {
        return bookedTimeSpans;
    }

    @Override
    public String toString() {
        return "Workplace{" +
                "id=" + id +
                ", room=" + assignedRoom +
                ", equipment=" + equipment +
                ", bookedTimeSpans=" + bookedTimeSpans +
                '}';
    }
}
