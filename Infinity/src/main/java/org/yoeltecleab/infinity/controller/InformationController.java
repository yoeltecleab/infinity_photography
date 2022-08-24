package org.yoeltecleab.infinity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.yoeltecleab.infinity.service.InformationService;
import org.yoeltecleab.infinity.service.UserServiceImpl;
import org.yoeltecleab.infinity.transfer.InformationDto;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class InformationController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    private InformationService informationService;

    @ModelAttribute("booking")
    public InformationDto informationDto() {
        return new InformationDto();
    }

    @GetMapping("/service")
    public String showInformationForm(Model model, Principal user) {
        if (user == null) return "service";
        model.addAttribute("forName", userService.name(user.getName()));
        return "service";
    }

    @PostMapping("/service")
    public String addBooking(@ModelAttribute("booking") @Valid InformationDto infoDto, BindingResult result) {
        if (result.hasErrors()) {
            return "service";
        }
        informationService.save(infoDto);
        return "redirect:/booking?success";
    }

    @GetMapping("/service/{id}")
    public String modificationForm(Model model, @PathVariable int id) {
        model.addAttribute("forId", informationService.infoById(id).getBookingId());
        return "service";
    }

    @PostMapping("/service/{id}")
    public String modifyBooking(@ModelAttribute("booking") @Valid InformationDto infoDto, BindingResult result, @PathVariable int id) {
        if (result.hasErrors()) {
            System.out.println(result.hasErrors());
            return "redirect:/service/{id}"; // todo Errors are not visible as it is redirecting to the original page !!!!!
        }
        informationService.modify(infoDto, id);
        return "redirect:/booking?modify_success";
    }

    @GetMapping("/booking/delete/{id}")
    public String removeBooking(@PathVariable int id) {
        informationService.deleteInformation(id);
        return "redirect:/booking?delete";
    }

    @GetMapping("/booking")
    public String cart(Model model, Principal principal) {
        if (principal == null) return "/bookings_cart";
        model.addAttribute("forName", "Hello " + userService.name(principal.getName()));
        model.addAttribute("cart", informationService.allBookingByEmail(principal.getName()));
        model.addAttribute("total", informationService.sumTotal(principal.getName()));
        return "/bookings_cart";
    }
}