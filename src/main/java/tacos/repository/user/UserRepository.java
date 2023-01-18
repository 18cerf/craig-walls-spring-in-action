package tacos.repository.user;

import org.springframework.data.repository.CrudRepository;
import tacos.data.user.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

}
