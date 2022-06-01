package hotel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotel.model.BookedRoom;
import hotel.model.Booking;
import hotel.model.Client;
import hotel.model.Hotel;
import hotel.model.Room;
import hotel.repository.BookedRoomRepository;
import hotel.repository.BookingRepository;
import hotel.repository.HotelRepository;
import hotel.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestAPIController {

	@Autowired
	HotelRepository hotelRepo;
	@Autowired
	RoomRepository roomRepo;
	@Autowired
	BookingRepository bookingRepo;
	@Autowired
	BookedRoomRepository bookedRoomRepo;

	@GetMapping("/list-room/{id}")
	public List<Room> getFreeRoomAPI(@PathVariable("id") Long id, HttpSession session) {
		String bD = (String) session.getAttribute("beginDate");
		String eD = (String) session.getAttribute("endDate");
		return roomRepo.findRoomByIdNotBookedRoom(id, bD, eD);
	}

	@GetMapping("/list-booking/{hotel_id}")
	public List<Booking> getFreeBookingAPI(@PathVariable("hotel_id") Long hotel_id, HttpSession session) {
		Client client = (Client) session.getAttribute("client");
		Long client_id = client.getId();
		List<Booking> bookings = new ArrayList<Booking>();

		Optional<Hotel> hotel = hotelRepo.findById(hotel_id);
		List<Room> rooms = hotel.get().getRooms();
		for (Room room : rooms) {
			List<BookedRoom> bookedRooms = room.getBookedRooms();
			for (BookedRoom bookedRoom : bookedRooms) {
				Booking booking = bookedRoom.getBooking();
				if (booking.getClient().getId() == client_id && !bookings.contains(booking)) {
					bookings.add(booking);
				}
			}
		}
		return bookings;
	}

	@GetMapping("/add-booking/{id}")
	public String addBooking(@PathVariable("id") Long id, HttpSession session) throws ParseException {
		Client client = (Client) session.getAttribute("client");
		Booking booking = new Booking();
		BookedRoom bookedRoom = new BookedRoom();
		Optional<Room> room = roomRepo.findById(id);
		
		String bD = (String) session.getAttribute("beginDate");
		String eD = (String) session.getAttribute("endDate");
		
		booking.setBeginDate(bD);
		booking.setEndDate(eD);
		booking.setClient(client);
		booking = bookingRepo.save(booking);

		bookedRoom.setDate(bD);
		bookedRoom.setRoom(room.get());
		bookedRoom.setBooking(booking);
		bookedRoomRepo.save(bookedRoom);
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = DateFor.parse(bD);
		
		calendar.setTime(beginDate);
		bD = DateFor.format((calendar.getTime()));
		while (bD.compareTo(eD) == -1) {
			calendar.add(Calendar.DATE, 1);
			bD = DateFor.format(calendar.getTime());

			BookedRoom bookedRoom1 = new BookedRoom();
			bookedRoom1.setDate(bD);
			bookedRoom1.setRoom(room.get());
			bookedRoom1.setBooking(booking);
			bookedRoomRepo.save(bookedRoom1);
		}

		return "success";
	}

	@GetMapping("/delete-booking")
	public String deleteBooking(@RequestParam Long id) {
		bookingRepo.deleteById(id);
		return "success";
	}

	
//	Admin
	@GetMapping("/select-hotel")
	public String selectHotel(@RequestParam Long id, HttpSession session) {
		Optional<Hotel> hotel = hotelRepo.findById(id);
		session.setAttribute("hotel", hotel.get());
		return "success";
	}

	@GetMapping("/option-statistical/{option}/{year}/{hotel_id}")
	public List<Double> optionStatistical(@PathVariable("option") int option, @PathVariable("year") int year,
			@PathVariable("hotel_id") Long hotel_id) throws ParseException {
		List<Double> list = new ArrayList<Double>();

		if (option == 0) {
			for (int i = 1; i <= 12; i++) {
				double sum = 0;
				List<BookedRoom> bookedRooms = (List<BookedRoom>) bookedRoomRepo.findAll();
				for (BookedRoom bookedRoom : bookedRooms) {
					if (bookedRoom.getRoom().getHotel().getId() == hotel_id) {
						String date = bookedRoom.getDate();

						SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
						Date date_format = DateFor.parse(date);
						if (date_format.getMonth() + 1 == i && date_format.getYear()+1900 == year) {
							sum += bookedRoom.getRoom().getPrice();
						}
					}

				}
				list.add(sum);
			}
		}
		if (option == 1) {
			for (int i = 1; i <= 4; i++) {
				double sum = 0;
				List<BookedRoom> bookedRooms = (List<BookedRoom>) bookedRoomRepo.findAll();
				for (BookedRoom bookedRoom : bookedRooms) {
					if (bookedRoom.getRoom().getHotel().getId() == hotel_id) {
						String date = bookedRoom.getDate();

						SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
						Date date_format = DateFor.parse(date);
						if (date_format.getMonth() + 1 >= (i - 1) * 3 + 1 && date_format.getMonth() + 1 <= i * 3
								&& date_format.getYear()+1900 == year) {
							sum += bookedRoom.getRoom().getPrice();
						}
					}

				}
				list.add(sum);
			}
		}
		if (option == 2) {
			for(int i=0; i<=year-2020; i++) {
			double sum = 0;
			List<BookedRoom> bookedRooms = (List<BookedRoom>) bookedRoomRepo.findAll();
			for (BookedRoom bookedRoom : bookedRooms) {
				if (bookedRoom.getRoom().getHotel().getId() == hotel_id) {
					String date = bookedRoom.getDate();

					SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
					Date date_format = DateFor.parse(date);
					if (date_format.getYear() + 1900 == 2020+i) {
						sum += bookedRoom.getRoom().getPrice();
					}
				}

			}
			list.add(sum);
			}
		}
		return list;
	}
}
