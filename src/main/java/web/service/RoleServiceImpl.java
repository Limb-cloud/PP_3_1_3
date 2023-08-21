package web.service;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.models.Role;
import web.models.User;
import web.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository repository;

  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository) {
    this.repository = roleRepository;
  }

  @Override
  public List<Role> listRoles() {
    return repository.findAll();
  }

  @Override
  @Transactional
  public void addRole(Role role) {
    repository.save(role);
  }

  @Override
  public Role getRoleByName(String name) {
    return null;
  }
}
