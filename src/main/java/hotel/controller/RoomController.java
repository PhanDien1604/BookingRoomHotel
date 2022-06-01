package hotel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hotel.model.BookedRoom;
import hotel.model.Booking;
import hotel.model.Client;
import hotel.model.Hotel;
import hotel.model.Image;
import hotel.model.Room;
import hotel.repository.BookedRoomRepository;
import hotel.repository.BookingRepository;
import hotel.repository.HotelRepository;
import hotel.repository.RoomRepository;

@Controller
@RequestMapping
public class RoomController {
	@Autowired
	private RoomRepository roomRepo;
	@Autowired
	private HotelRepository hotelRepo;
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private BookedRoomRepository bookedRoomRepo;

	@GetMapping("/admin/room")
	public String room(Room room, Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}
		if (session.getAttribute("hotel") == null) {
			return "redirect:/admin/login";
		}
		Hotel hotel = (Hotel) session.getAttribute("hotel");

		Optional<Hotel> hotel1 = hotelRepo.findById(hotel.getId());
		List<Room> rooms = hotel1.get().getRooms();
		model.addAttribute("rooms", rooms);
		return "admin/room";
	}

//	home
	@GetMapping("/list-room")
	public String listRoom(Model model, HttpSession session) {
		if (session.getAttribute("client") == null) {
			return "redirect:/login";
		}
		if (session.getAttribute("address").toString() != "") {
			String address = session.getAttribute("address").toString();
			List<Hotel> hotels = (List<Hotel>) hotelRepo.findByAddressOrNameContaining(address);
			model.addAttribute("hotels", hotels);

			model.addAttribute("beginDate", session.getAttribute("beginDate"));
			model.addAttribute("endDate", session.getAttribute("endDate"));
			return "client/listRoom";
		}

		return "client/home";

	}

	@GetMapping("/infor-room")
	public String inforRoom(@RequestParam Long id, Model model, HttpSession session) {
		if (session.getAttribute("client") == null) {
			return "redirect:/login";
		}
		Optional<Room> room = roomRepo.findById(id);
		List<Image> images = room.get().getImages();
		model.addAttribute("images", images);
		model.addAttribute("room", room.get());
		return "client/inforRoom";
	}
	@GetMapping("/select-room")
	public String selectRoom(@RequestParam Long id, Model model, HttpSession session) throws ParseException {
		if (session.getAttribute("client") == null) {
			return "redirect:/login";
		}
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

		return "redirect:/list-room";
	}
	
}
