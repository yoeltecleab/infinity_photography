//package org.yoeltecleab.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.Mapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.yoeltecleab.model.User;
//import org.yoeltecleab.transfer.UserDto;
//import org.yoeltecleab.service.UserService;
//import org.yoeltecleab.service.UserServiceImpl;
//
//import javax.validation.Valid;
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/register")
//    public String form(Model model) {
//        return "index";
//    }
//
//    @PostMapping("/register")
//    public String register(@Valid UserDto userDto) {
////        User user = new User();
////        user.setEmail(email);
////        user.setFirstName(fname);
////        user.setLastName(lname);
////        user.setPassword(Password);
//        userService.save(userDto);
//        return "index";
//    }
//}
package org.yoeltecleab.infinity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yoeltecleab.infinity.model.User;
import org.yoeltecleab.infinity.service.UserService;
import org.yoeltecleab.infinity.transfer.UserDto;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "signup";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", "Error", "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "signup";
        }
        userService.save(userDto);
        return "redirect:/login?success";

    }

    @PostMapping("/login?success")
    public String loginUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {

        if (result.hasErrors()) {
            return "login";
        }
        return "index";
    }
}

