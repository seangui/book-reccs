package com.bookreccs.controller;

import javax.validation.Valid;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookreccs.dto.UserDto;
import com.bookreccs.entity.User;
import com.bookreccs.service.UserService;

@Controller
@RequestMapping("/register")
public class CreateAccountController {
	
	@Autowired
	private UserService userService; 
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// Pre processor, pre process each web request to our controller. 
	// We use this specifically in our case to trim strings remove 
	// leading and trailing white space. 
	// However we can also use webdatabinder to convert data to specific types
	// for example we can't proccess 10/08/1996 as a String, but we can use 
	// WebDataBinder to convert any String as a Date 
	@InitBinder // this annotation helps spring identify webdatabinder method 
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/create-acc")
	public String showCreateAccPage(Model theModel)
	{
		theModel.addAttribute("userDto", new UserDto());
		
		return "create-acc";
	}
	
	// important that the BindingResult comes RIGHT AFTER the form being validated
	@PostMapping("/process-regristration-form")
	public String processRegistrationForm(
			@Valid @ModelAttribute("userDto") UserDto theUserDto,
			BindingResult theBindingResult,
			Model theModel)
	{
		String username = theUserDto.getUsername();
		logger.info("Processing registration form for: " + username);
		
		// form validation
		if(theBindingResult.hasErrors())
			return "create-acc";
		
		// check the database if user already exists 
		User existing = userService.findByUserName(username);
		if(existing != null)
		{
			theModel.addAttribute("userDto", new UserDto());
			theModel.addAttribute("registrationError", "User name already exists");
			
			logger.warning("Username already exists");
			return "create-acc"; 
		}
		
		// create user account 
		userService.save(theUserDto);
		
		logger.info("Successfully created user: " + username);
		
		return "confirmation-page";
	}
	
	
}
