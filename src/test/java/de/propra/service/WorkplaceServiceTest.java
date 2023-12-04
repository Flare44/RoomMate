package de.propra.service;



import de.propra.domain.Equipment;
import de.propra.domain.TimeSpan;
import de.propra.domain.Workplace;
import de.propra.domain.WorkplaceRepository;
import de.propra.service.testresources.WorkplaceFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static de.propra.service.testresources.WorkplaceFactory.WORKPLACES_MULTIPLE_COLLISIONS;
import static de.propra.service.testresources.WorkplaceFactory.WORKPLACES_NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class WorkplaceServiceTest {

    private WorkplaceRepository repository;
    private WorkplaceService service;

    // funktionieren alle Methoden normal?
    @BeforeEach
    private void setUp() {
        repository = mock(WorkplaceRepository.class);
        when(repository.getAllWorkplaces()).thenReturn(WORKPLACES_NORMAL);
        service = new WorkplaceService(repository);
    }

    @AfterEach
    private void resetRepository(TestInfo testInfo) {
        //reset(repository);
        // workplace zugreifen, dort die TimeSpans und dort den TimeSpan suchen und entfernen
        if(testInfo.getDisplayName().equals("addBooking(Workplace-ID, TimeSpan) adds the TimeSpan to the Workplace") ||
                testInfo.getDisplayName().equals("After successful booking, another booking for the same workplace in the same time fails")
        ) {
            List<TimeSpan> bookedTimeSpans = repository.getAllWorkplaces().stream()
                    .filter(workplace -> workplace.getId().equals(2L))
                    .findFirst().get().getBookedTimeSpans();
            bookedTimeSpans.removeIf(timeSpan -> timeSpan.getStartTime()
                    .isEqual(LocalDateTime.of(2023, 10, 25, 17, 31))
                    && timeSpan.getEndTime().
                    isEqual(LocalDateTime.of(2023, 10, 25, 21, 5)));
        }
    }

    @Test
    @DisplayName("getAllWorkplaces()-Method returns all workplaces from repository")
    public void test_0() {
        // Arrange in setUp()-Method


        // Act
        List<Workplace> actual = service.getAllWorkplaces();

        // Assert
        assertThat(actual).containsAll(WORKPLACES_NORMAL);
    }

    @Test
    @DisplayName("getAvailableWorkplaces(TimeSpan)-Method returns all available workplaces from repository, if no collision")
    public void test_1() {
        // Arrange
        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 17, 31),
                LocalDateTime.of(2023, 10, 25, 21, 5)
        );

        // Act
        List<Workplace> actual = service.getAvailableWorkplaces(timeSpan);

        // Assert
        assertThat(actual).containsAll(WORKPLACES_NORMAL);
    }

    @Test
    @DisplayName("getAvailableWorkplaces(TimeSpan)-Method returns less workplaces, if there is a collision (collision: overlap after)")
    public void test_2() {
        // Arrange
        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 17, 0),
                LocalDateTime.of(2023, 10, 25, 21, 5)
        );

        // Act
        List<Workplace> actual = service.getAvailableWorkplaces(timeSpan);
        List<Workplace> expected = new ArrayList<>(WORKPLACES_NORMAL);
        expected.remove(1);

        // Assert
        assertThat(actual).hasSize(WORKPLACES_NORMAL.size() - 1);
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("getAvailableWorkplaces(TimeSpan)-Method returns less workplaces, if there is a collision (collision: overlap before)")
    public void test_3() {
        // Arrange
        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 7, 15),
                LocalDateTime.of(2023, 10, 25, 14, 30)
        );

        // Act
        List<Workplace> actual = service.getAvailableWorkplaces(timeSpan);
        List<Workplace> expected = new ArrayList<>(WORKPLACES_NORMAL);
        expected.remove(1);

        // Assert
        assertThat(actual).hasSize(WORKPLACES_NORMAL.size() - 1);
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("getAvailableWorkplaces(TimeSpan)-Method returns less workplaces, if there is a collision (collision: overlap before and after)")
    public void test_4() {
        // Arrange
        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 7, 15),
                LocalDateTime.of(2023, 10, 25, 21, 30)
        );

        // Act
        List<Workplace> actual = service.getAvailableWorkplaces(timeSpan);
        List<Workplace> expected = new ArrayList<>(WORKPLACES_NORMAL);
        expected.remove(1);

        // Assert
        assertThat(actual).hasSize(WORKPLACES_NORMAL.size() - 1);
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("getAvailableWorkplaces(TimeSpan)-Method returns less workplaces, if there is a collision (collision: overlap exactly)")
    public void test_5() {
        // Arrange


        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 8, 0),
                LocalDateTime.of(2023, 10, 25, 17, 30)
        );

        // Act
        List<Workplace> actual = service.getAvailableWorkplaces(timeSpan);
        List<Workplace> expected = new ArrayList<>(WORKPLACES_NORMAL);
        expected.remove(1);

        // Assert
        assertThat(actual).hasSize(WORKPLACES_NORMAL.size() - 1);
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("getAvailableWorkplaces(TimeSpan)-Method returns less workplaces, if there are two collision (collision: overlap before)")
    public void test_6() {
        // Arrange
        repository = mock(WorkplaceRepository.class);
        when(repository.getAllWorkplaces()).thenReturn(WORKPLACES_MULTIPLE_COLLISIONS);
        service = new WorkplaceService(repository);

        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 7, 15),
                LocalDateTime.of(2023, 10, 25, 14, 30)
        );

        // Act
        List<Workplace> actual = service.getAvailableWorkplaces(timeSpan);
        List<Workplace> expected = new ArrayList<>(WORKPLACES_MULTIPLE_COLLISIONS);
        expected.remove(7);
        expected.remove(6);
        expected.remove(2);
        expected.remove(1);
        expected.remove(0);

        // Assert
        assertThat(actual).hasSize(WORKPLACES_MULTIPLE_COLLISIONS.size() - 5);
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("getAvailableWorkplaces(TimeSpan)-Method returns less workplaces, if there are two collision (collision: overlap after)")
    public void test_7() {
        // Arrange
        repository = mock(WorkplaceRepository.class);
        when(repository.getAllWorkplaces()).thenReturn(WORKPLACES_MULTIPLE_COLLISIONS);
        service = new WorkplaceService(repository);

        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 17, 0),
                LocalDateTime.of(2023, 10, 25, 21, 5)
        );

        // Act
        List<Workplace> actual = service.getAvailableWorkplaces(timeSpan);
        List<Workplace> expected = new ArrayList<>(WORKPLACES_MULTIPLE_COLLISIONS);
        expected.remove(7);
        expected.remove(6);
        expected.remove(2);
        expected.remove(1);
        expected.remove(0);

        // Assert
        assertThat(actual).hasSize(WORKPLACES_MULTIPLE_COLLISIONS.size() - 5);
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("getAvailableWorkplaces(TimeSpan, Equipment)-Method returns workplaces, that match the equipment-requirements")
    public void test_8() {
        // Arrange

        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 17, 31),
                LocalDateTime.of(2023, 10, 25, 21, 5)
        );

        List<Equipment> equipmentRequirements = List.of(Equipment.MOUSE, Equipment.KEYBOARD);

        // Act
        List<Workplace> actual = service.getAvailableWorkplaces(timeSpan, equipmentRequirements);
        List<Workplace> expected = new ArrayList<>(WORKPLACES_NORMAL);
        expected.remove(8);
        expected.remove(3);
        expected.remove(2);

        // Assert
        assertThat(actual).hasSize(WORKPLACES_NORMAL.size() - 3);
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("getAvailableWorkplaces(TimeSpan, Equipment)-Method returns no workplaces, if equipment-requirements cannot be matched")
    public void test_9() {
        // Arrange

        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 17, 31),
                LocalDateTime.of(2023, 10, 25, 21, 5)
        );

        List<Equipment> equipmentRequirements = List.of(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_HDMI, Equipment.MONITOR_DP);

        // Act
        List<Workplace> actual = service.getAvailableWorkplaces(timeSpan, equipmentRequirements);
        List<Workplace> expected = new ArrayList<>();


        // Assert
        assertThat(actual).hasSize(0);
        assertThat(actual).containsAll(expected);
    }

    // test 10 freilassen, für dual-monitor equipment. Testen, ob das klappt

    @Test
    @DisplayName("getAvailableWorkplaces(TimeSpan, Room-ID)-Method returns workplaces, that match a certain room-ID")
    public void test_11() {
        // Arrange

        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 17, 31),
                LocalDateTime.of(2023, 10, 25, 21, 5)
        );

        Long roomId = 4L;

        // Act
        List<Workplace> actual = service.getAvailableWorkplaces(timeSpan, roomId);
        List<Workplace> expected = new ArrayList<>(List.of(WORKPLACES_NORMAL.get(8)));


        // Assert
        assertThat(actual).hasSize(1);
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("getAvailableWorkplaces(TimeSpan, Equipment, Room-ID)-Method returns workplaces, that match the requirements")
    public void test_12() {
        // Arrange

        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 17, 31),
                LocalDateTime.of(2023, 10, 25, 21, 5)
        );

        List<Equipment> equipmentRequirements = List.of(Equipment.MOUSE, Equipment.KEYBOARD, Equipment.MONITOR_HDMI);

        Long roomId = 3L;

        // Act
        List<Workplace> actual = service.getAvailableWorkplaces(timeSpan, equipmentRequirements, roomId);
        List<Workplace> expected = new ArrayList<>(List.of(WORKPLACES_NORMAL.get(6), WORKPLACES_NORMAL.get(4)));


        // Assert
        assertThat(actual).hasSize(2);
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("addBooking(Workplace-ID, TimeSpan) adds the TimeSpan to the Workplace")
    public void test_13() {
        // Arrange

        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 17, 31),
                LocalDateTime.of(2023, 10, 25, 21, 5)
        );

        Long workplaceId = 2L;

        assertThat(service.getAvailableWorkplaces(timeSpan)).hasSize(WORKPLACES_NORMAL.size()); // Veranschaulichung

        // Act
        Boolean workplaceIsAvailable = service.addBooking(workplaceId, timeSpan);

        // Assert
        assertThat(workplaceIsAvailable).isTrue();
        assertThat(service.getAvailableWorkplaces(timeSpan)).hasSize(WORKPLACES_NORMAL.size() - 1);
    }

    @Test
    @DisplayName("After successful booking, another booking for the same workplace in the same time fails")
    public void test_14() {
        // Arrange

        TimeSpan timeSpan = new TimeSpan(
                LocalDateTime.of(2023, 10, 25, 17, 31),
                LocalDateTime.of(2023, 10, 25, 21, 5)
        );

        Long workplaceId = 2L;


        // Act
        Boolean workplaceIsAvailable = service.addBooking(workplaceId, timeSpan);

        assertThat(workplaceIsAvailable).isTrue();
        assertThat(service.getAvailableWorkplaces(timeSpan)).hasSize(WORKPLACES_NORMAL.size() - 1);


        // Assert
        // Buchung ist danach nicht mehr gültig
        workplaceIsAvailable = service.addBooking(workplaceId, timeSpan);
        assertThat(workplaceIsAvailable).isFalse();
    }
}
