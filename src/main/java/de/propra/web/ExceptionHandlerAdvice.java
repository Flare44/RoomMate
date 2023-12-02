package de.propra.web;

import de.propra.exception.DateOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DateOrderException.class)
    public String handleDateOrderException(DateOrderException exception, RedirectAttributes redirectAttributes) {
        // füge Exception-Nachricht hinzu
        redirectAttributes.addFlashAttribute("dateOrderExceptionMessage", exception.getMessage());
        System.out.println(exception.getMessage());
        return "redirect:/roommate/user/bookings/new";
    }
}
