package hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotel.model.BookedRoom;
import hotel.model.Booking;
import hotel.model.Client;
import hotel.model.Hotel;
import hotel.model.Image;
import hotel.model.Room;
import hotel.repository.BookingRepository;
import hotel.repository.ClientRepository;

@Controller
@RequestMapping
public class BookingController {
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private ClientRepository clientRepo;
	
//	home
	@GetMapping("/list-booking")
	public String listBooking(Model model, HttpSession session) {
		if(session.getAttribute("client") == null) {
			return "redirect:/login";
		}
		Client client = (Client) session.getAttribute("client");
		
		Optional<Client> client1 = clientRepo.findById(client.getId());
		List<Booking> bookings = client1.get().getBookings();
		List<Hotel> hotels = new ArrayList();
		for (Booking booking : bookings) {
			List<BookedRoom> bookedRooms = booking.getBookedRooms();
			for (BookedRoom bookedRoom : bookedRooms) {
				Room room = bookedRoom.getRoom();
				Hotel hotel = room.getHotel();
				
				if(!hotels.contains(hotel)) {
					hotels.add(hotel);
				}
				
			}
		}
		model.addAttribute("hotels",hotels);
		
		return "client/booking";
	}
	@GetMapping("/list-booking/room")
	public String listBookedRoom(@RequestParam Long id, Model model, HttpSession session) {
		if(session.getAttribute("client") == null) {
			return "redirect:/login";
		}
		Optional<Booking> booking = bookingRepo.findById(id);
		List<BookedRoom> bookedRooms = booking.get().getBookedRooms();
		Room room = bookedRooms.get(0).getRoom();
		List<Image> images = room.getImages();
		model.addAttribute("room",room);
		model.addAttribute("images",images);
		model.addAttribute("booking",booking.get());
		return "client/bookingRoom";
	}
	@GetMapping("/delete-booking")
	public String deleteBooking(@RequestParam Long id) {
		bookingRepo.deleteById(id);
		return "redirect:/list-booking";
	}
}
