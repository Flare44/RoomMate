package de.propra.domain;

import java.util.Arrays;
import java.util.List;

public enum Equipment {
    MOUSE("Maus"),
    KEYBOARD("Tastatur"),
    MONITOR_HDMI("Monitor (HDMI Anschluss)"),
    MONITOR_DP("Monitor (Displayport Anschluss)"),
    MONITOR_USB_C("Monitor (USB-C Anschluss"),
    DESK_HEIGHT_ADJUSTABLE("Höhenverstellbarer Tisch");

    private final String name;
    private Equipment(String name) {
        this.name = name;
    }

    public static List<String> getEquipment() {
        return Arrays.stream(values())
                .map(value -> value.name)
                .toList();
    }


    public String getName() {
        return name;
    }





}
