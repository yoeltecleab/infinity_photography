package org.yoeltecleab.infinity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yoeltecleab.infinity.service.CustomerService;
import org.yoeltecleab.infinity.service.InformationService;
import org.yoeltecleab.infinity.service.UserServiceImpl;
import org.yoeltecleab.infinity.transfer.CustomerDto;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/billing")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    InformationService informationService;

    @ModelAttribute("billing")
    public CustomerDto customerDto() {
        return new CustomerDto();
    }

    @GetMapping
    public String showBillingForm(Principal user, Model model) {
        if (user == null) return "billing";
        model.addAttribute("forName", userService.name(user.getName()));
        return "billing";
    }

    @PostMapping
    public String getBillingInformation(@ModelAttribute("billing") @Valid CustomerDto customerDto, BindingResult result, Principal user) {
        if (result.hasErrors()) {
            return "billing";
        }
        if (user != null) {
            informationService.deleteAll(user.getName());
        }
        customerService.save(customerDto);
        return "redirect:/?success";
    }
}
