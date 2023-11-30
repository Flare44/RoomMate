package de.propra.exception;

public class RoomNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Du bist ein Hurensohn, der Raum existiert nicht!";
    }
}
