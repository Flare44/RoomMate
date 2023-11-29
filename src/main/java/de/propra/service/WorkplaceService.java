package de.propra.service;

import de.propra.domain.Equipment;
import de.propra.domain.Workplace;
import de.propra.domain.WorkplaceRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

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
}
