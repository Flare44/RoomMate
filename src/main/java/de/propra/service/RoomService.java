package de.propra.service;

import de.propra.domain.Room;
import de.propra.domain.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> getAllRooms() {
        return repository.getAllRooms();
    }
    public boolean roomIsAvailable(Long roomId) {
        return repository.getAllRooms().stream()
                .anyMatch(room -> room.getId().equals(roomId));
    }


    public String getRoomName(Long id) {
        return repository.getAllRooms().stream()
                .filter(room -> room.getId().equals(id))
                .findFirst().orElseThrow().getName();
    }

}
