package hotel.repository;

import javax.validation.Valid;

import org.springframework.data.repository.CrudRepository;

import hotel.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsernameAndPassword(String username, String password);

}
