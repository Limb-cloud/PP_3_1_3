package web.service;

import java.util.List;
import java.util.Set;
import web.models.Role;
import web.models.User;

public interface RoleService {
  List<Role> listRoles();
  void addRole(Role role);
  Role getRoleByName(String name);
}
