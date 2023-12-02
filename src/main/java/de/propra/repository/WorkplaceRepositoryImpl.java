package de.propra.repository;

import de.propra.domain.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkplaceRepositoryImpl implements WorkplaceRepository {

    private final List<Workplace> workplaces = generateExampleWorkplaces();

    public static void main(String[] args) {
        WorkplaceRepositoryImpl workplaceRepository = new WorkplaceRepositoryImpl();
        System.out.println(workplaceRepository.getAllWorkplaces());
    }

    @Override
    public List<Workplace> getAllWorkplaces() {
        return workplaces;
    }

    private List<Workplace> generateExampleWorkplaces() {
        List<Workplace> workplaces = new ArrayList<>();
        RoomRepository roomRepository = new RoomRepositoryImpl();

        List<Room> allRooms = roomRepository.getAllRooms();
        long id = 1;
        for(long j = 1; j <= allRooms.size(); j++) {
            for(long i = 1; i <= allRooms.get((int) j - 1).getWorkplaces(); i++) {
                // so oft erstelle ich Workplaces pro Raum
                workplaces.add(new Workplace(id, j, generateExampleEquipment(), generateExampleTimespans()));
                id++;
            }
        }
        return workplaces;
    }

    private List<Equipment> generateExampleEquipment() {
        List<Equipment> equipment = new ArrayList<>();

        int amountEquipment = (int) (Math.random() * (Equipment.values().length - 2)) + 1;

        List<Integer> bisherigeIndices = new ArrayList<>();
        int indexForItem;

        for(int i = 0; i < amountEquipment; i++) {
            do {
                indexForItem = (int) (Math.random() * Equipment.values().length);
            } while(bisherigeIndices.contains(indexForItem));

            bisherigeIndices.add(indexForItem);

            equipment.add(Equipment.values()[indexForItem]);
        }
        return equipment;
    }

    private List<TimeSpan> generateExampleTimespans() {
        List<TimeSpan> timeSpans = new ArrayList<>();

        LocalDateTime start1 = LocalDateTime.of(2023, 12, 10, 8, 17);
        LocalDateTime end1 = LocalDateTime.of(2023, 12, 10, 17, 17);
        TimeSpan timeSpan1 = new TimeSpan(start1, end1);

        LocalDateTime start2 = LocalDateTime.of(2023, 12, 12, 8, 5);
        LocalDateTime end2 = LocalDateTime.of(2023, 12, 12, 17, 0);
        TimeSpan timeSpan2 = new TimeSpan(start2, end2);

        LocalDateTime start3 = LocalDateTime.of(2023, 12, 14, 7, 0);
        LocalDateTime end3 = LocalDateTime.of(2023, 12, 14, 16, 12);
        TimeSpan timeSpan3 = new TimeSpan(start3, end3);

        LocalDateTime start4 = LocalDateTime.of(2023, 12, 16, 14, 0);
        LocalDateTime end4 = LocalDateTime.of(2023, 12, 16, 18, 0);
        TimeSpan timeSpan4 = new TimeSpan(start4, end4);

        TimeSpan[] timespan = {timeSpan1, timeSpan2, timeSpan3, timeSpan4};

        int amountTimespans = (int) (Math.random() * 4) + 1; // +1 entfernen wenn man das nicht möchte. So Min von 1. Also jeder Platz einmal gebucht

        List<Integer> bisherigeIndices = new ArrayList<>();
        int index;

        for(int i = 0; i < amountTimespans; i++) {
            do {
                index = (int) (Math.random() * 4);
            } while(bisherigeIndices.contains(index));
            bisherigeIndices.add(index);

            timeSpans.add(timespan[index]);
        }
        return timeSpans;

    }
}
