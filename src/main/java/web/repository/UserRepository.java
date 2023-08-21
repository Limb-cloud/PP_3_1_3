package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query(value = "Select u from User u left join fetch u.roles where u.username=:userName")
  User findByUserName(@Param("userName") String userName);
}
