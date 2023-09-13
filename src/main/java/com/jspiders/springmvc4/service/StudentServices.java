package com.jspiders.springmvc4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.jspiders.springmvc4.pojo.StudentPojo;
import com.jspiders.springmvc4.repository.StudentRepository;


@Service
public class StudentServices {
       @Autowired
       private StudentRepository repository;

	public StudentPojo searchStudent(int id) {
		StudentPojo pojo = repository.searchStudent(id);
		return pojo;
	}

	public StudentPojo addStudent(String name, String email, long contact, String address) {
		StudentPojo pojo = repository.addStudent(name,email,contact,address);		
		return pojo;
	}

	public List<StudentPojo> findAllStudents() {
		List<StudentPojo> students = repository.findAllStudents();
		return students;
	}

	public StudentPojo removeStudent(int id) {
		StudentPojo pojo = repository.removeStudent(id);
		return pojo;
	}

	public StudentPojo updateStudent(int id, String name, String email, long contact, String address) {
		StudentPojo pojo = repository.updateStudent(id,name,email,contact,address);
		return pojo;
	}
       
}
