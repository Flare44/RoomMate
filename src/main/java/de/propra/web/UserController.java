package de.propra.web;

import de.propra.domain.*;
import de.propra.service.RoomService;
import de.propra.service.ValidationService;
import de.propra.service.WorkplaceService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
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
    public String add(@ModelAttribute("information") BookingInformation information, RedirectAttributes redirectAttributes) {
        if(redirectAttributes.getAttribute("dateOrderExceptionMessage") != null) {
            System.out.println(redirectAttributes.getAttribute("dateOrderExceptionMessage"));
        }
        return "user/new";
    }

    // Anmerkung: Hier kann keine Redirect-View verwendet werden, da sonst die Fehlermeldungen verschwinden
    @PostMapping("/bookings/new")
    public String addForm(@Valid @ModelAttribute("information") BookingInformation information, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "user/new";
        }

        // custom validation
        ValidationService.validateDate(information.getStartTime(), information.getEndTime());
        if(information.getRoom() != null) ValidationService.validateRoom(roomService, Long.parseLong(information.getRoom()));

        redirectAttributes.addFlashAttribute("information", information);
        redirectAttributes.addFlashAttribute("workplaces", getAvailableWorkplaces(information));

        return "redirect:/roommate/user/bookings/new";
    }

    // TODO: Implement
    @GetMapping("/bookings/new/confirm/{workplaceId}")
    public String confirmBooking(@PathVariable("workplaceId") Long workplaceId, Model model, Long roomId, String roomName ,@DateTimeFormat(pattern = "yyyy-MM-dd''HH:mm") LocalDateTime startTime, @DateTimeFormat(pattern = "yyyy-MM-dd''HH:mm")LocalDateTime endTime, Equipment[] equipmentList) {
        model.addAttribute("workplaceId", workplaceId);
        model.addAttribute("roomName", roomName);
        model.addAttribute("roomId", roomId);
        model.addAttribute("equipmentList", equipmentList);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);


//        model.addAttribute("equipment", bookingInformation.getEquipment());
//        model.addAttribute("startTime", bookingInformation.getStartTime());
//        model.addAttribute("endTime", bookingInformation.getEndTime());

        // Führe Post request durch, wenn bestätigt wird. Dann soll das geadded werden

        return "user/confirm";
    }


    @PostMapping("/bookings/new/confirm")
    public RedirectView confirm(Long workplaceId, @DateTimeFormat(pattern = "yyyy-MM-dd''HH:mm") LocalDateTime startTime, @DateTimeFormat(pattern = "yyyy-MM-dd''HH:mm")LocalDateTime endTime) {
        TimeSpan timeSpan = new TimeSpan(startTime, endTime);
        if(workplaceService.addBooking(workplaceId, timeSpan)) {
            System.out.println("Buchung des Platzes mit der ID : " + workplaceId + " von: " + startTime + " bis: " + endTime + " war erfolgreich!");
        }
        return new RedirectView("/roommate/user/bookings");
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
