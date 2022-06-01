package hotel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hotel.model.User;
import hotel.repository.UserRepository;

@Controller
@RequestMapping
public class UserController {
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("admin/login")
	public String login(User user, Model model) {
		model.addAttribute("user",user);
		return "admin/login";
	}
	
	@PostMapping("admin/login")
	public String checkLogin(User user, Errors errors, Model model, HttpSession session) {
		if (userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword()) == null) { 
			 model.addAttribute("user",user);
			 model.addAttribute("fail", "Tài khoản hoặc mật khẩu sai"); 
			 return "admin/login"; 
		}
		user = userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		session.setAttribute("user", user);
		session.setAttribute("hotel", user.getHotel());
		return "redirect:/admin/hotel";
	}
}
