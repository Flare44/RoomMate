package de.propra.domain;

public class Room {

    private final Long id;
    private final String name;
    private final Long workplaces;

    public Room(Long id, String name, Long workplaces) {
        this.id = id;
        this.name = name;
        this.workplaces = workplaces;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getWorkplaces() {
        return workplaces;
    }


    @Override
    public String toString() {
        return name;
    }
}
