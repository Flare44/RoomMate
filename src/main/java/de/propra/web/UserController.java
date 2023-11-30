package de.propra.web;

import de.propra.domain.BookingInformation;
import de.propra.domain.Equipment;
import de.propra.domain.RoomRepository;
import de.propra.service.RoomService;
import de.propra.service.WorkplaceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import static de.propra.domain.Equipment.getEquipment;
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

    @GetMapping("/start")
    public String start() {
        return "user/start";
    }

    @GetMapping("/bookings")
    public String bookings() {
        return "user/bookings";
    }

    @GetMapping("/bookings/new")
    public String add(Model model, @Valid @ModelAttribute("information") BookingInformation information, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user/new";
        }

        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("equipment", values());

        System.out.println(information);

        if(information.getEquipment() == null) {
            model.addAttribute("workplaces", workplaceService.getAllWorkplaces());
        } else {
            model.addAttribute("workplaces", workplaceService.getWorkplacesWithEquipment(information.getEquipment()));
        }

        return "user/new";
    }
}
