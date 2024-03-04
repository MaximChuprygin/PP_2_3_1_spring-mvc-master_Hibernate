package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final UserService userService;

    public PeopleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers (Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "people/users";
    }

    @GetMapping("/{id}")
    public String readUser (@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.readUser(id));
        return "people/user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "people/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        return "people/new";
        }
        userService.createUser(user);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model,@PathVariable("id") long id) {
        model.addAttribute("user", userService.readUser(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser (@ModelAttribute("user") @Valid User user,BindingResult bindingResult,@PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        userService.updateUser(id,user);
        return "redirect:/people";
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/people";
    }

}
