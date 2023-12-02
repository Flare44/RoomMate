package de.propra.exception;

public class InvalidStartTimeException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Startzeitpunkt muss in Zukunft oder Gegenwart liegen";
    }
}
