package de.propra.repository;

import de.propra.domain.Equipment;
import de.propra.domain.EquipmentRepository;
import de.propra.domain.Workplace;
import de.propra.domain.WorkplaceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkplaceRepositoryImpl implements WorkplaceRepository {
    private final List<Workplace> workplaces = List.of(
            new Workplace(1L, 20L, List.of(
                    new Equipment("Tastatur"),
                    new Equipment("Maus"),
                    new Equipment("Monitor [USB-C Anschluss]")
            )),
            new Workplace(2L, 25L, List.of(
                    new Equipment("Tastatur"),
                    new Equipment("Maus"),
                    new Equipment("Monitor [HDMI Anschluss]")
            )),
            new Workplace(3L, 54L, List.of(
                    new Equipment("Tastatur"),
                    new Equipment("Maus"),
                    new Equipment("Höhenverstellbarer Tisch")
            ))
    );

    @Override
    public List<Workplace> getAllWorkplaces() {
        return workplaces;
    }
}
