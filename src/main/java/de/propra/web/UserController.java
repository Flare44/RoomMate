package de.propra.web;

import de.propra.service.WorkplaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roommate/user")
public class UserController {
    private final WorkplaceService workplaceService;

    public UserController(WorkplaceService workplaceService) {
        this.workplaceService = workplaceService;
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
    public String add(Model model) {
        model.addAttribute("workplaces", workplaceService.getAllWorkplaces());
        return "user/new";
    }
}
