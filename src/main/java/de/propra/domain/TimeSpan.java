package de.propra.domain;

import java.time.LocalDateTime;

public class TimeSpan {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;


    public TimeSpan(LocalDateTime startTime, LocalDateTime endTime) {
        this.endTime = endTime;
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
