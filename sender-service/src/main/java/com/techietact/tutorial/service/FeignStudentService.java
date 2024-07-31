package com.techietact.tutorial.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.techietact.tutorial.config.FeignClientConfig;
import com.techietact.tutorial.model.Student;

@FeignClient(name="receiver-service",url="http://localhost:8888",configuration = FeignClientConfig.class)
public interface FeignStudentService {

	@PostMapping
	Student createStudent(@RequestBody Student student);
	
	@GetMapping("/{studentId}")
	Student viewStudent(@PathVariable("studentId") int studentId);
	
	@PutMapping
	Student updateStudent(@RequestBody Student student);
	
	@PatchMapping
	Student partialUpdateStudent(@RequestBody Student student);
	
	@DeleteMapping("/{studentId}")
	void deleteStudent(@PathVariable("studentId")int studentId);
	
	@GetMapping
	List<Student> listStudents();
	
}
