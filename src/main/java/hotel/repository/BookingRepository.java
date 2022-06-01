package hotel.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import hotel.model.Booking;

public interface BookingRepository extends  CrudRepository<Booking, Long>{
	

}
