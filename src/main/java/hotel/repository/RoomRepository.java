package hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hotel.model.Room;
@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
	
	@Query(value = "SELECT room.* FROM room WHERE hotel_id = ?1 "
			+ "AND room.id NOT IN "
			+ "(SELECT booked_room.room_id FROM booked_room "
			+ "WHERE room.id = booked_room.room_id "
			+ "AND booked_room.date >= ?2 AND booked_room.date <= ?3"
			+ ")", nativeQuery = true)
	List<Room> findRoomByIdNotBookedRoom(Long hotel_id, String beginDate, String endDate);
}
