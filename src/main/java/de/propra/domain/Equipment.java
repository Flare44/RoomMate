package de.propra.domain;

public class Equipment {
    private final String name;
    public Equipment(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
