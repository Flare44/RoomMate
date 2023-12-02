package de.propra.service;

import de.propra.domain.Equipment;
import de.propra.domain.TimeSpan;
import de.propra.domain.Workplace;
import de.propra.domain.WorkplaceRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WorkplaceService {
    private final WorkplaceRepository repository;

    public WorkplaceService(WorkplaceRepository repository) {
        this.repository = repository;
    }

    public List<Workplace> getAllWorkplaces() {
        return repository.getAllWorkplaces();
    }

    public Workplace getWorkplace(Long id) throws Exception {
        return repository.getAllWorkplaces().stream()
                .filter(workplace -> workplace.getId() == id)
                .findFirst()
                .orElseThrow(Exception::new);
    }

    public List<Workplace> getWorkplacesWithEquipment(List<Equipment> equipment) {
        return repository.getAllWorkplaces().stream()
                .filter(workplace -> new HashSet<>(workplace.getEquipment()).containsAll(equipment))
                .toList();
    }

    public boolean addBooking(Long id, TimeSpan timeSpan) {
        if(workplaceIsAvailable(id, timeSpan)) {
            // add
            List<TimeSpan> timeSpans = repository.getAllWorkplaces().stream()
                    .filter(workplace -> workplace.getId().equals(id))
                    .findFirst().get().getBookedTimeSpans();
            timeSpans.add(timeSpan);
            return true;
        }

        return false;
    }

    public List<Workplace> getAvailableWorkplaces(TimeSpan timeSpan) {
        List<Workplace> workplaces = repository.getAllWorkplaces().stream()
                .filter(workplace -> workplace.getBookedTimeSpans().stream()
                        .allMatch(timespan -> timespan.getEndTime().isBefore(timeSpan.getStartTime()) || timespan.getStartTime().isAfter(timeSpan.getEndTime()))
                ).toList();


        // wenn wir keinen Zeitraum haben, dann ist der Arbeitsplatz ja auch vorhanden
//        workplaces.addAll(repository.getAllWorkplaces().stream()
//                .filter(workplace -> workplace.getBookedTimeSpans().isEmpty())
//                .toList());

        return workplaces;
    }

    public List<Workplace> getAvailableWorkplaces(TimeSpan timeSpan, List<Equipment> equipment) {
        return getAvailableWorkplaces(timeSpan).stream()
                .filter(workplace -> workplace.getEquipment().containsAll(equipment))
                .toList();
    }

    private boolean workplaceIsAvailable(Long id, TimeSpan timeSpan) {
        List<TimeSpan> timeSpans = repository.getAllWorkplaces().stream()
                .filter(workplace -> workplace.getId().equals(id))
                .findFirst()
                .orElseThrow()
                .getBookedTimeSpans();

        return timeSpans.stream()
                .allMatch(timespan -> timespan.getEndTime().isBefore(timeSpan.getStartTime()) || timespan.getStartTime().isAfter(timeSpan.getEndTime()));
    }

}
