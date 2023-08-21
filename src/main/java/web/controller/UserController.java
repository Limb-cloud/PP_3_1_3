package web.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.UserService;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService uService) {
    this.userService = uService;
  }

  @GetMapping
  public String printUpdateUser(Model model, Principal principal) {
    model.addAttribute("user",
        userService.getUserByUsername(principal.getName()));
    return "user";
  }
}
