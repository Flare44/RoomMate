package de.propra.service;

import de.propra.domain.*;
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

    public List<Workplace> getAvailableWorkplaces(TimeSpan timeSpan, Long roomId) {
        return getAvailableWorkplaces(timeSpan).stream()
                .filter(workplace -> workplace.getAssignedRoom().getId().equals(roomId))
                .toList();
    }

    public List<Workplace> getAvailableWorkplaces(TimeSpan timeSpan, List<Equipment> equipment, Long roomId) {
        return getAvailableWorkplaces(timeSpan, equipment).stream()
                .filter(workplace -> workplace.getAssignedRoom().getId().equals(roomId))
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


    public boolean addBooking(Long workplaceId, TimeSpan timeSpan) {
        if(workplaceIsAvailable(workplaceId, timeSpan)) {
            // add
            List<TimeSpan> timeSpans = repository.getAllWorkplaces().stream()
                    .filter(workplace -> workplace.getId().equals(workplaceId))
                    .findFirst().get().getBookedTimeSpans();
            timeSpans.add(timeSpan);
            return true;
        }
        return false;
    }

}
