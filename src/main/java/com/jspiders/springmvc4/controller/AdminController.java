package com.jspiders.springmvc4.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jspiders.springmvc4.pojo.AdminPojo;
import com.jspiders.springmvc4.service.AdminServices;

@Controller
public class AdminController {
       @Autowired
       public AdminServices services;
       
       @GetMapping("/createAccount")
        public String createAccountPage(ModelMap map) {
    	   AdminPojo pojo = services.getAdmin();
    	   
    	   if(pojo != null) {
    		   map.addAttribute("msg", "Account already exists..!");
    		   return "Login";
    	   }
			return "CreateAccount";   
       }
       
       // Create Account Controller
       @PostMapping("/createAccount")
       public String createAccount(@RequestParam String password,
    		                        @RequestParam String username,ModelMap map) {
    	   AdminPojo pojo=services.createAccount(username,password);
    	   
    	   // Success flow
    	   if(pojo != null) {
    		   map.addAttribute("msg","Account created successfully..!");
    	   return "Login";
    	   }
    	   
    	   // Failure Flow
    	   map.addAttribute("msg","Account Not");
    	   return "Login";
       }
       
        // login controller
       
       @PostMapping("/login")
   	public String login(@RequestParam String username,
   						@RequestParam String password,
   						ModelMap map, HttpSession session) {
   		AdminPojo pojo = services.login(username, password);
   		//Success
   		if (pojo != null) {
   			session.setAttribute("login", pojo);
   			session.setMaxInactiveInterval(10);
   			return "Home";
   		}
   		map.addAttribute("msg", "Invalid username or password..!");
   		return "Login";
   	}
   	
   	//Logout Controller
   		@GetMapping("/logout")
   		public String logout(ModelMap map,
   								HttpSession session) {
   			session.invalidate();
   			map.addAttribute("msg", "Logged out successfully..!");
   			return "Login";
   		}

   }
