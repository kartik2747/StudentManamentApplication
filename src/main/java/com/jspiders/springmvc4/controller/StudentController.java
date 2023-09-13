package com.jspiders.springmvc4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.jspiders.springmvc4.pojo.AdminPojo;
import com.jspiders.springmvc4.pojo.StudentPojo;
import com.jspiders.springmvc4.service.StudentServices;

@Controller
public class StudentController {
       @Autowired
       public StudentServices services;
       
       
       //Home Page Controller
       
       @GetMapping("/home")
       public String home(@SessionAttribute(name = "login", required = false)AdminPojo admin,ModelMap map) {
    	   if(admin != null) {
    		   return "Home";
    	   }
    	map.addAttribute("msg", "Session inactive. Login to proceed..!");
		return "Login";
       }
       
       @GetMapping("/add")
       public String add() {
		return "Add";
    	   
       }
       
       // Add Student Controller
       @PostMapping("/add")
       public String addStudent(@RequestParam String name, 
    		                   @RequestParam String email,
    		                   @RequestParam long contact,
    		                   @RequestParam String address , ModelMap map) {
    	  StudentPojo pojo = services.addStudent(name,email,contact,address);
       
                  // 1. Success Flow For Adding Student
           
       if(pojo != null) {
    	   map.addAttribute("msg", "Data Inserted Successfully");
    	   List<StudentPojo> students = services.findAllStudents();
    	   map.addAttribute("students", students);
    	   return "Add";
       }
                // 2. Failure Case
       map.addAttribute("msg", "Data Not Inserted");
       List<StudentPojo> students =  services.findAllStudents();
         if(!students.isEmpty())
        	 map.addAttribute("students", students);
       return "Add";  
       }
       
       // Search Page Controller
       
       @GetMapping("/search")
       public String searchPage() {
    	   return "Search";
       }
       
       // Search Student Controller
       
       @PostMapping("/search")
       public String searchStudent(@RequestParam int id , ModelMap map) {
    	 StudentPojo pojo =  services.searchStudent(id);
    	 
    	 // Success Flow
    	 if(pojo != null) {
    		 map.addAttribute("students", pojo);
    	     map.addAttribute("msg", "Student data found");
    	       return "Search";
    	 }
    	       
    	       // Failure Case
        map.addAttribute("msg", "Student data not found");
		return "Search";
       }
       
       // Remove Student Page
       
       @GetMapping("/remove")
       public String remove() {
    	   return "Remove";
       }
       @PostMapping("/remove")
       public String removeStudent(@RequestParam int id, ModelMap map) {
    	StudentPojo pojo = services.removeStudent(id);
    	List<StudentPojo> students = services.findAllStudents();
    	
    	       // 1. Success Flow
    	
    	if(pojo != null) {
    		map.addAttribute("msg","Data Remove Successfully");
    		map.addAttribute("students", students);
    		return "Remove";
    	}
    	  
    	      // 2. Failure Flow
    	
    	map.addAttribute("msg","Data does not exist.." );
    	map.addAttribute("students", students);
		return "Remove";
       }
      
       
          // Update Student Page
       
       @GetMapping("/update")
       public String update() {
    	   return "Update";
       }
       
       //  Update from Controller 
       
       @PostMapping("/update")
       public String updateStudent(@RequestParam int id, ModelMap map) {
    	   
    	  StudentPojo pojo = services.searchStudent(id);
    	  
    	  // Success Flow
    	  if(pojo != null) {
    		  map.addAttribute("student", pojo);
    		  return "Update";
    	  }
    	  
    	  // failure Flow
    	  List<StudentPojo> students = services.findAllStudents();
    	  map.addAttribute("students", students);
    	  map.addAttribute("msg", "Student Data not Found");
		return "Update";
    	   
       }
       
         // Update student controller
           @PostMapping("/updateStudent")
           public String updateStudent(@RequestParam int id,
					@RequestParam String name,
					@RequestParam String email,
					@RequestParam long contact,
					@RequestParam String address,
					ModelMap map) {
        	 StudentPojo pojo =  services.updateStudent(id,name,email,contact,address);
        	
        	 // Successs flow
        	 
        	 if(pojo != null) {
        		 List<StudentPojo> students = services.findAllStudents();
        		map.addAttribute("msg","Data updated successfully..!");
        		map.addAttribute("students", students);
        		 return "Update";
        	 }
        	  
        	 // Failure flow
        	 
        	 List<StudentPojo> students = services.findAllStudents();
        	 map.addAttribute("msg", "Data not update");
        	 map.addAttribute("students", students);	 
			 return "Update";   
           }
       
}

