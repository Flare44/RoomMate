package de.propra.service;

import de.propra.exception.DateOrderException;
import de.propra.exception.InvalidStartTimeException;
import de.propra.exception.InvalidTimeSpanException;
import de.propra.exception.RoomNotFoundException;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

public class ValidationService {

    public static void validateDate(LocalDateTime start, LocalDateTime end) {
        validateStartIsBeforeEnd(start, end);
        validateStartIsCurrent(start);
        validateDifferenceBetweenStartAndEndIsLessThanADay(start, end);
    }

    private static void validateStartIsBeforeEnd(LocalDateTime start, LocalDateTime end) {
        if(start.isAfter(end)) throw new DateOrderException();
    }


    private static void validateStartIsCurrent(LocalDateTime start) {
        // minus offset to include user-input-delay
        if(start.isBefore(LocalDateTime.now().minusMinutes(15))) throw new InvalidStartTimeException();
    }


    private static void validateDifferenceBetweenStartAndEndIsLessThanADay(LocalDateTime start, LocalDateTime end) {
        if(start.plusHours(24).isBefore(end)) throw new InvalidTimeSpanException();
    }

    public static void validateRoom(RoomService service, Long roomId) {
        if(!service.roomIsAvailable(roomId)) throw new RoomNotFoundException();
    }
}
