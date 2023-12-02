package de.propra.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.List;

public class BookingInformation {


    @NotNull
    private @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startTime;
    @NotNull
    private @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endTime;

    private String room;

    private List<Equipment> equipment;


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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
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
