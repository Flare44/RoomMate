package de.propra.repository;

import de.propra.domain.Equipment;
import de.propra.domain.EquipmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipmentRepositoryImpl implements EquipmentRepository {
    private final List<Equipment> equipment = List.of(
            new Equipment("Tastatur"),
            new Equipment("Maus"),
            new Equipment("Monitor [HDMI Anschluss]"),
            new Equipment("Monitor [Displayport Anschluss]"),
            new Equipment("Monitor [USB-C Anschluss]"),
            new Equipment("Höhenverstellbarer Tisch")
    );
    @Override
    public List<Equipment> getFullEquipment() {
        return equipment;
    }
}
