  package com.techietact.tutorial.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techietact.tutorial.model.Student;
import com.techietact.tutorial.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sender")
@RequiredArgsConstructor
public class SenderController {

	private final StudentService studentService ;
	
	//private final FeignStudentService studentService ; 

	@GetMapping("/{studentId}")
	public ResponseEntity<Student> viewStudent(@PathVariable("studentId") int studentId) {
		Student student = studentService.viewStudent(studentId);
		if(student==null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(student);
	}

	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		student = studentService.createStudent(student);
		if(student==null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(student);
	}

	@PutMapping
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		student = studentService.updateStudent(student);
		if(student==null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(student);
	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") int studentId){
		studentService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping
	public ResponseEntity<Student> patchStudent(@RequestBody Student student) {
		student = studentService.partialUpdateStudent(student);
		if(student==null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(student);
	}

	@GetMapping
	public ResponseEntity<List<Student>> listStudents() {
		return ResponseEntity.ok(studentService.listStudents());
	}

}
