package de.propra.exception;

public class DateOrderException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Startzeitpunkt muss vor Endzeitpunkt sein";
    }
}
