package de.propra.exception;

public class InvalidTimeSpanException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Der Zeitraum darf maximal 24 Stunden lang sein";
    }
}
