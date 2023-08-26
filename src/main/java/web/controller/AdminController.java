package web.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

  private final UserService userService;
  private final RoleService roleService;

  private static final String REDIRECT_MAIN_PAGE = "redirect:/admin";

  @Autowired
  public AdminController(UserService userService, RoleService roleService) {
    this.userService = userService;
    this.roleService = roleService;
  }

  @GetMapping
  public String adminPanel(Model model) {
    model.addAttribute("users", userService.listUsers());
    return "admin_panel";
  }

  @GetMapping(value = "/user")
  public String printAddUser(Model model) {
    model.addAttribute("roleList", roleService.listRoles());
    model.addAttribute("user", new User());
    return "add_user";
  }

  @GetMapping(value = "/user/{id}")
  public String printUpdateUser(Model model, @PathVariable("id") long id) {
    model.addAttribute("roleList", roleService.listRoles());
    model.addAttribute("user", userService.getUserById(id));
    return "update_user";
  }

  @PostMapping("/user")
  public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
      Model model, @RequestParam(required = false) String role) {
    if (userService.isUniqueUsername(user.getUsername())) {
      bindingResult.addError(new FieldError("user", "username", "Данный логин уже используется"));
    }

    if (bindingResult.hasErrors()) {
      model.addAttribute("roleList", roleService.listRoles());
      return "add_user";
    }

    userService.addUser(user, role);
    return REDIRECT_MAIN_PAGE;
  }

  @PutMapping(value = "/user/{id}")
  public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
      Model model, @RequestParam(required = false) String role) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("roleList", roleService.listRoles());
      return "update_user";
    }

    userService.updateUser(user, role);
    return REDIRECT_MAIN_PAGE;
  }

  @DeleteMapping(value = "/{id}")
  public String removeUser(@PathVariable("id") long id) {
    userService.removeUser(id);
    return REDIRECT_MAIN_PAGE;
  }
}
