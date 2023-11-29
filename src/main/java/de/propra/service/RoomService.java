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

}
