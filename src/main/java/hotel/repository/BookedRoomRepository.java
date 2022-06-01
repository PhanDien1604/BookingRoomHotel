package hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import hotel.model.BookedRoom;

public interface BookedRoomRepository 	extends  CrudRepository<BookedRoom, Long>{
	
	@Query("SELECT br FROM BookedRoom br WHERE br.booking.id = ?1")
	List<BookedRoom> findByBookingId(Long booking_id);
}
