package hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import hotel.model.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Long>{
	
	@Query("SELECT h FROM Hotel h WHERE h.address LIKE %?1% OR h.name LIKE %?1%")
	List<Hotel> findByAddressOrNameContaining(String address);
}
