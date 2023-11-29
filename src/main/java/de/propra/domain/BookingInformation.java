package de.propra.domain;

import java.time.LocalDateTime;
import java.util.List;

public class BookingInformation {


    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Room room;

    private List<Equipment> equipment;

    public BookingInformation(LocalDateTime startTime, LocalDateTime endTime, Room room, List<Equipment> equipment) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.equipment = equipment;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "BookingInformation{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", room='" + room + '\'' +
                ", equipment=" + equipment +
                '}';
    }
}
