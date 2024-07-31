package com.techietact.tutorial.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.techietact.tutorial.model.Student;

@Controller
public class HomeController {
 	
	@GetMapping("/{studentId}")
	public ResponseEntity<Student> viewStudent(@PathVariable("studentId") int studentId) throws Exception{
		Student student = new Student();
		student.setStudentId(studentId);
		student.setName("Ragavan");
		student.setEmail("ragavan@gmail.com");
		if(studentId<1) {
			throw new Exception("Invalid Student Id");
		}
		return ResponseEntity.ok(student);
	}
	
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student ){
		student.setStudentId(8);
		return ResponseEntity.ok(student);
	}
	
	@PutMapping
	public ResponseEntity<Student> updateStudent(@RequestBody Student student ){
		student.setName(student.getName() + " - Edited");
		return ResponseEntity.ok(student);
	}
	
	@DeleteMapping("/{studentId}")
	public ResponseEntity<Boolean> deleteStudent(@PathVariable("studentId") int studentId) throws Exception{
		if(studentId<1) {
			throw new Exception("Invalid Student Id");
		}
		return ResponseEntity.ok(true);
	}
	
	@PatchMapping
	public ResponseEntity<Student> patchStudent(@RequestBody Student student ){
		student.setName(student.getName() + " - patched");
		return ResponseEntity.ok(student);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> listStudents(){
		List<Student> students = new ArrayList<>();
		Student student = new Student();
		student.setStudentId(1);
		student.setName("Ragavan");
		student.setEmail("ragavan@gmail.com");
		students.add(student);
		student = new Student();
		student.setStudentId(2);
		student.setName("Durga");
		student.setEmail("durga@gmail.com");
		students.add(student);
		student = new Student();
		student.setStudentId(3);
		student.setName("Sellamuthu");
		student.setEmail("sellamuthu@gmail.com");
		students.add(student);
		student = new Student();
		student.setStudentId(4);
		student.setName("Nilan");
		student.setEmail("mathi@gmail.com");
		students.add(student);
		
		return ResponseEntity.ok(students);
	}
	
}
