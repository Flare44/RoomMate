package de.propra.web;

import de.propra.domain.*;
import de.propra.service.RoomService;
import de.propra.service.WorkplaceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;

import static de.propra.domain.Equipment.values;

@Controller
@RequestMapping("/roommate/user")
public class UserController {
    private final WorkplaceService workplaceService;
    private final RoomService roomService;


    public UserController(WorkplaceService workplaceService, RoomService roomService) {
        this.workplaceService = workplaceService;
        this.roomService = roomService;
    }

    @ModelAttribute("rooms")
    public List<Room> setRooms() {
        return roomService.getAllRooms();
    }

    @ModelAttribute("equipment")
    public List<Equipment> setEquipment() {
        return Arrays.stream(values()).toList();
    }


    @GetMapping("/start")
    public String start() {
        return "user/start";
    }

    @GetMapping("/bookings")
    public String bookings() {
        return "user/bookings";
    }

    @GetMapping("/bookings/new")
    public String add(@ModelAttribute("information") BookingInformation information) {
        return "user/new";
    }

    @PostMapping("/bookings/new")
    public RedirectView addForm(@Valid @ModelAttribute("information") BookingInformation information, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return new RedirectView("/roommate/user/bookings/new");
        }

        redirectAttributes.addFlashAttribute("information", information);
        redirectAttributes.addFlashAttribute("workplaces", getAvailableWorkplaces(information));

        return new RedirectView("/roommate/user/bookings/new");
    }

    // TODO: Implement
    @GetMapping("/bookings/new/confirm")
    public String confirmBooking() {
        return "user/confirm";
    }








    private List<Workplace> getAvailableWorkplaces(BookingInformation information) {
        TimeSpan timeSpan = new TimeSpan(information.getStartTime(), information.getEndTime());
        List<Workplace> availableWorkplaces;

        if (information.getEquipment() == null && !information.getRoom().equals("")) {
            availableWorkplaces = workplaceService.getAvailableWorkplaces(timeSpan, Long.parseLong(information.getRoom())); // Raum aber nicht Equip
        } else if(information.getEquipment() == null && information.getRoom().equals("")){
            availableWorkplaces = workplaceService.getAvailableWorkplaces(timeSpan); // Kein Raum und kein Equip
        } else if(information.getEquipment() != null && information.getRoom().equals("")) {
            availableWorkplaces = workplaceService.getAvailableWorkplaces(timeSpan, information.getEquipment()); // Equip aber kein Raum
        } else {
            availableWorkplaces = workplaceService.getAvailableWorkplaces(timeSpan, information.getEquipment(), Long.parseLong(information.getRoom())); // Raum und Equip
        }
        return availableWorkplaces;
    }
}
