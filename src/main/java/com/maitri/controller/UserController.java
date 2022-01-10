package com.maitri.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.maitri.model.JwtRequest;
import com.maitri.model.User;
import com.maitri.service.JwtService;
import com.maitri.service.UserService;
/*
 * This controller implements all the rest end points.
 */
@Controller
public class UserController {
	 @Autowired
	 private UserService userService;
	 @Autowired
	 private JwtController jwtController;
	 @Autowired
	 private JwtService jwtService;
	 //After Constructing user and role table,it initializes admin & user role.Also creates credentials for admin.
	 @PostConstruct
	 public void initRoleAndUser() {
		 userService.initRoleAndUser();
	 }
	 //Register new user to database
	 @PostMapping({"/registerNewUser"})
	 public User registerNewUser(@RequestBody User user) {
       return userService.registerNewUser(user);
	 }
	 //Redirects to registration page.
	 @GetMapping("/")
	 public String showIndexPage1(Model model) {
	   model.addAttribute("command", new User());
	   return "index.jsp";
	 }	  
	 //After registering user,redirects user to login function.	   
	 @RequestMapping(value = "/register", method = RequestMethod.POST)
	 public String saveUser(@ModelAttribute("adduser") User user) throws Exception {
		userService.registerNewUser(user);
		return "redirect:/login";
	 }
	 //Invalidate JWT token and return login page.
	 @GetMapping("/login")
	 public String login(Model model) {
		jwtController.jwtToken="";
	   	model.addAttribute("command", new JwtRequest());
	   	return "login.jsp";
	 }
    //Retrive data from database.
	@RequestMapping("/readuser")
    public String showReadContactPage(Model model) {
		model.addAttribute("users1", userService.findAll());
	    return "readuser.jsp";
    }
	//Only Admin can delete data from database.Implement delete function
	@RequestMapping(value = "/delete/{id}")
	@PreAuthorize("hasRole('Admin')")
	public  String deleteContact(@PathVariable String id) {
    	userService.deleteByUsername(id);
    	return "redirect:/readuser";
	}
	//Update the userDetail.Only Admin can perform update operation.
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public  String updateContact(@PathVariable String id, @ModelAttribute("adduser") User user) throws Exception {
       User u1= userService.updateUser(id, user);
       return "redirect:/readuser";
    }
    //Return update user jsp page.
    @RequestMapping(value = "/updateuser/{id}"  )
    @PreAuthorize("hasRole('Admin')")
    public String showUpdateContactPage(@PathVariable String id,Model model,HttpServletResponse response) {
        model.addAttribute("id", id);
        model.addAttribute("command", userService.findById(id).orElse(null));
	   	return "updateuser.jsp";
    }
}
