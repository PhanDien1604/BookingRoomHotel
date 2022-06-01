package hotel.repository;

import org.springframework.data.repository.CrudRepository;

import hotel.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{

	Client findByUsername(String username);

	Client findByUsernameAndPassword(String username, String password);
}
