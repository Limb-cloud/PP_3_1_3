package web.initializer;

import java.util.HashSet;
import java.util.Set;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import web.models.Role;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

@Component
public class Initialization implements ApplicationListener<ContextRefreshedEvent> {

  private final RoleService roleService;
  private final UserService userService;

  public Initialization(RoleService roleService, UserService userService) {
    this.roleService = roleService;
    this.userService = userService;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    Role roleAdmin = new Role();
    Role roleUser = new Role();
    Set<Role> roles = new HashSet<>();

    roleAdmin.setName("ROLE_ADMIN");
    roleUser.setName("ROLE_USER");

    roleService.addRole(roleAdmin);
    roleService.addRole(roleUser);

    roles.add(roleAdmin);
    roles.add(roleUser);

    User admin = new User(
        "Admin",
        "Admin",
        "admin@localhost.ru",
        18,
        "admin",
        "admin",
        roles
    );

    userService.addUser(admin);
  }
}
