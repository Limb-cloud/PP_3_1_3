package web.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import web.models.User;

public interface UserService extends UserDetailsService {
  List<User> listUsers();
  void addUser(User user);
  void updateUser(User user);
  void removeUser(Long id);
  User getUserById(Long id);
  User getUserByUsername(String userName);
}
