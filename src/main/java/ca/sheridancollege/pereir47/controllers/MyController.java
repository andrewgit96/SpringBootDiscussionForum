package ca.sheridancollege.pereir47.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.pereir47.beans.Messages;
import ca.sheridancollege.pereir47.beans.UserRegistration;
import ca.sheridancollege.pereir47.database.DatabaseAccess;

@Controller
public class MyController {
	
	//username: 'Andrew' pass: 'pass' for admin role. 
	//added some JS when adding a message to the thread. User hovers over the date and time and it will display it automatically. 
	//I tested the hover many many times and it works but if browser not loading properly it may not display. 
	//other minor JS like in home page, hover over welcome message will change colour
	//register a user and alert message will display 
	
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/login")
	public String getLogin() {
	return "login";
	}
	@GetMapping("/")
	public String getIndex() {
	return "index";
	}
	@GetMapping("/home")
	public String getHome(HttpSession s, Model model, Thread thread) {
		
		
		model.addAttribute("threads", da.getAllThreads());
		return "home";
	}
	
	@GetMapping("/view/{id}")
	public String viewThread(Model model, @PathVariable Long id, @ModelAttribute Messages message, @ModelAttribute UserRegistration user) {
		
		model.addAttribute("messages", da.threadId(id));

		return "view-thread";
	}
	
	@PostMapping("/view/{id}")
	public String submitPost(Model model, @ModelAttribute Messages message) {
		da.insertMessage(message);
		
		model.addAttribute("messages", message);
		model.addAttribute("threads", da.getAllThreads());
		return "home";
	}
	
	
	@GetMapping("/admin")
	public String getAdmin(Model model, @ModelAttribute Thread thread) {
	model.addAttribute("thread", thread);
	return "admin";
	}
	
	@PostMapping("/addThread")
	public String addThread(Model model, ca.sheridancollege.pereir47.beans.Thread thread) {
		da.insertThread(thread);
		model.addAttribute("threads", da.getAllThreads());
		return "home";
	}
	
	
	
	
	@GetMapping("/register")
	public String register(Model model, UserRegistration user) {
	model.addAttribute("user", user);
	return "register";
	}
	@PostMapping("/register")
	public String processRegister(@ModelAttribute UserRegistration user) {
	List<GrantedAuthority> authorities = new
	ArrayList<GrantedAuthority>();
	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	String encodedPassword =
	bCryptPasswordEncoder.encode(user.getPassword());
	User newuser = new User(user.getUsername(), encodedPassword,
	authorities);
	jdbcUserDetailsManager.createUser(newuser);
	return "redirect:/";
	}
	@GetMapping("/access-denied")
	public String accessDenied() {
	return "/error/access-denied.html";
	}

}
