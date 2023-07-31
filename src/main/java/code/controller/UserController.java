package code.controller;

import code.model.User;
import code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> userList = userService.getAll();
        model.addAttribute("userList", userList);
        return "users";
    }

    @GetMapping("/add-user")
    public String addUserPage (Model model) {
        model.addAttribute("user", new User() );
        return "add-user";
    }

    @PostMapping("/")
    public String addUser (@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add-user";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/update-user/{id}")
    public String updateUserPage (Model model, @PathVariable long id) {
        model.addAttribute("user", userService.get(id));
        return "update-user";
    }

    @PatchMapping("/update/{id}")
    public String updateUser (@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "update-user";
        }
        userService.update(user);
        return "redirect:/users";
    }

}
