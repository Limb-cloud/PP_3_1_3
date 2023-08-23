package web.service;

import java.util.List;
import web.models.Role;

public interface RoleService {

  List<Role> listRoles();

  void addRole(Role role);

  Role getRoleByName(String name);
}
