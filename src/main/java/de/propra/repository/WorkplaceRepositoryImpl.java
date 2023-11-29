package de.propra.repository;

import de.propra.domain.Equipment;
import de.propra.domain.Workplace;
import de.propra.domain.WorkplaceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkplaceRepositoryImpl implements WorkplaceRepository {
    private final List<Workplace> workplaces = List.of(
            new Workplace(1L, 1L, List.of(
                    Equipment.MOUSE
            ))
            /*,
            new Workplace(2L, 1L, List.of(
                    new Equipment("Tastatur"),
                    new Equipment("Maus"),
                    new Equipment("Monitor [HDMI Anschluss]")
            )),
            new Workplace(3L, 2L, List.of(
                    new Equipment("Tastatur"),
                    new Equipment("Maus"),
                    new Equipment("Monitor [USB-C Anschluss]"),
                    new Equipment("Höhenverstellbarer Tisch")
            )),
            new Workplace(4L, 2L, List.of(
                    new Equipment("Tastatur"),
                    new Equipment("Maus"),
                    new Equipment("Monitor [USB-C Anschluss]"),
                    new Equipment("Höhenverstellbarer Tisch")
            )),
            new Workplace(5L, 2L, List.of(
                    new Equipment("Tastatur"),
                    new Equipment("Maus"),
                    new Equipment("Höhenverstellbarer Tisch")
            )),

             */
    );

    @Override
    public List<Workplace> getAllWorkplaces() {
        return workplaces;
    }
}
