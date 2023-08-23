package web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;
import web.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final PasswordEncoder encoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.repository = userRepository;
    this.encoder = passwordEncoder;
  }

  @Override
  public List<User> listUsers() {
    return repository.findAll();
  }

  @Override
  @Transactional
  public void addUser(User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    repository.save(user);
  }

  @Override
  @Transactional
  public void updateUser(User user) {
    repository.save(user);
  }

  @Override
  @Transactional
  public void removeUser(Long id) {
    repository.deleteById(id);
  }

  @Override
  public User getUserById(Long id) {
    return repository.getById(id);
  }

  @Override
  public User getUserByUsername(String username) {
    return repository.findByUserName(username);
  }

  @Override
  public boolean isUniqueUsername(String userName) {
    return getUserByUsername(userName) != null;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getUserByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("Пользователь не найден");
    }

    return user;
  }
}
