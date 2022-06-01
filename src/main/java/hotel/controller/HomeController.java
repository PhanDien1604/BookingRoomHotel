package hotel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hotel.model.Booking;
import hotel.model.Hotel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping
public class HomeController {

	@GetMapping("/")
	public String home(Model model, Booking booking, Hotel hotel) {
		model.addAttribute("hotel",hotel);
		model.addAttribute("booking",booking);
		return "client/home";
	}
	@PostMapping("/searchRoom")
	public String searchRoom(Hotel hotel, @Valid Booking booking, Model model, Errors errors, HttpSession session)
			throws ParseException {
		if (session.getAttribute("client") == null) {
			return "redirect:/login";
		}
		if (hotel.getAddress() == null || hotel.getAddress().isEmpty()) {
			model.addAttribute("errorAddress", "Địa điểm cần phải nhập");
			model.addAttribute("hotel", hotel);
			return "/client/home";
		}

		if (errors.hasErrors()) {
			model.addAttribute("booking", booking);
			return "/client/home";
		}

		String bD = booking.getBeginDate();
		String eD = booking.getEndDate();
		Date beginDate = new SimpleDateFormat("MM/dd/yyyy").parse(bD);
		Date endDate = new SimpleDateFormat("MM/dd/yyyy").parse(eD);

		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");

		bD = DateFor.format(beginDate);
		eD = DateFor.format(endDate);

		session.setAttribute("address", hotel.getAddress());
		session.setAttribute("beginDate", bD);
		session.setAttribute("endDate", eD);
		return "redirect:/list-room";
	}
//	Thong ke
	@GetMapping("/admin/turnover")
	public String turnover(Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}
		return "admin/turnover";
	}
}
