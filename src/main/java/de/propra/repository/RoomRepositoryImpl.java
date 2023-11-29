package de.propra.repository;

import de.propra.domain.Room;
import de.propra.domain.RoomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    private final List<Room> rooms = List.of(
            new Room(1L, "Empfang", 2L),
            new Room(2L, "HR", 3L),
            new Room(3L, "Sales", 8L),
            new Room(4L, "Marketing", 5L),
            new Room(5L, "Entwicklung", 3L)
    );
    @Override
    public List<Room> getAllRooms() {
        return rooms;
    }
}
