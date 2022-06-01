package hotel.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotel.model.Booking;
import hotel.model.Client;
import hotel.repository.ClientRepository;

@Controller
@RequestMapping
public class ClientController {
	@Autowired
	private ClientRepository clientRepo;

	@GetMapping("/admin/client")
	public String client(Model model, Client client, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}
		List<Client> clients = (List<Client>) clientRepo.findAll();
		model.addAttribute("clients", clients);
		return "admin/client";
	}
	@GetMapping("/admin/client/infor-client")
	public String inforClient(@RequestParam Long id,Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}
		Optional<Client> client = clientRepo.findById(id);
		List<Booking> bookings = client.get().getBookings();
		model.addAttribute("bookings", bookings);
		return "admin/inforClient";
	}
//	home
	@GetMapping("/login")
	public String login(Model model, Client client) {
		model.addAttribute("client", client);
		return "client/login";
	}

	@PostMapping("/login")
	public String postLogin(@Valid Client client, Errors errors, Model model, HttpSession session) {
		if (client.getUsername() == null || client.getUsername().isEmpty()) {
			model.addAttribute("errorUsername", "Tên tài khoản bắt buộc phải nhập");
			model.addAttribute("client", client);
			return "/client/login";
		}

		if (client.getPassword() == null || client.getPassword().isEmpty()) {
			model.addAttribute("errorPassword", "Mật khẩu bắt buộc phải nhập");
			model.addAttribute("client", client);
			return "/client/login";
		}

		if (clientRepo.findByUsernameAndPassword(client.getUsername(), client.getPassword()) == null) {
			model.addAttribute("client", client);
			model.addAttribute("fail", "Tài khoản hoặc mật khẩu sai");
			return "/client/login";
		}
		client = clientRepo.findByUsernameAndPassword(client.getUsername(), client.getPassword());
		session.setAttribute("client", client);
		return "redirect:/";
	}

	@GetMapping("/register")
	public String register(Model model, Client client) {
		model.addAttribute("client", client);
		return "client/register";
	}

	@PostMapping("/register")
	public String postRegister(@Valid Client client, Errors errors, Model model) {
		if (client.getName() == null || client.getName().isEmpty()) {
			model.addAttribute("errorName", "Họ và tên bắt buộc phải nhập");
			model.addAttribute("client", client);
			return "/client/register";
		}
		if (client.getUsername() == null || client.getUsername().isEmpty()) {
			model.addAttribute("errorUsername", "Tên tài khoản bắt buộc phải nhập");
			model.addAttribute("client", client);
			return "/client/register";
		}

		if (client.getPassword() == null || client.getPassword().isEmpty()) {
			model.addAttribute("errorPassword", "Mật khẩu bắt buộc phải nhập");
			model.addAttribute("client", client);
			return "/client/register";
		}

		if (clientRepo.findByUsername(client.getUsername()) != null) {
			model.addAttribute("client", client);
			model.addAttribute("fail", "Tài khoản đã tồn tại");
			return "/client/register";
		}
		client.setAddress("Null");
		client.setTel("Null");
		client = clientRepo.save(client);
		return "redirect:/login";
	}

	@GetMapping("/profile")
	public String profile(Model model, HttpSession session) {
		if (session.getAttribute("client") == null) {
			return "redirect:/login";
		}
		Client client = (Client) session.getAttribute("client");
		model.addAttribute("client", client);
		return "client/profile";
	}

	@PostMapping("/profile")
	public String updateProfile(@Valid Client client, Errors errors, Model model, HttpSession session) {
		if (errors.hasErrors()) {
			model.addAttribute("client", client);
			return "client/profile";
		}
		System.out.println(client.getId());
		client = clientRepo.save(client);
		session.setAttribute("client", client);
		return "redirect:/profile";
	}
}
