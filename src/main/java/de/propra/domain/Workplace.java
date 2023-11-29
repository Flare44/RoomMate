package de.propra.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Workplace {
    private final Long id;
    private final Long roomId;
    private final List<Equipment> equipment = new ArrayList<>();

    public Workplace(Long id, Long roomId) {
        this.id = id;
        this.roomId = roomId;
    }

    public Workplace(Long id, Long roomId, List<Equipment> equipment) {
        this.id = id;
        this.roomId = roomId;
        this.equipment.addAll(equipment);
    }

    public Workplace(Long id, Long roomId, Equipment... equipment) {
        this.id = id;
        this.roomId = roomId;
        this.equipment.addAll(Arrays.asList(equipment));
    }

    public Long getId() {
        return id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }
}
