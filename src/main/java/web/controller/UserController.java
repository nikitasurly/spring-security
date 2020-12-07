package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/login")
	public String getLoginPage() {
		return "login";
	}

	@GetMapping(value = "/user")
	public String getUserPage(ModelMap model) {
		model.addAttribute("user", userService.getUserByLogin());
		return "user";
	}

}