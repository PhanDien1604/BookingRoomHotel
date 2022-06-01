package hotel.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hotel.model.Hotel;
import hotel.model.User;
import hotel.repository.HotelRepository;


@Controller
@RequestMapping
public class HotelController {
	@Autowired
	private HotelRepository hotelRepo;
	
	@GetMapping("/admin/hotel")
	public String hotel(Hotel hotel, Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}
		User user = (User) session.getAttribute("user");
		List<Hotel> hotels = (List<Hotel>) hotelRepo.findAll();
		model.addAttribute("hotels", hotels);
		model.addAttribute("hotel", hotel);
		return "admin/hotel";
	}
}
