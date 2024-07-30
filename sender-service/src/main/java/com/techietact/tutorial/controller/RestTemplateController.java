  package com.techietact.tutorial.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.client.RestTemplate;

import com.techietact.tutorial.model.Student;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rt")
public class RestTemplateController {

	private final RestTemplate restTemplate;

	@Value("${other.service.url}")
	private String BASE_URL;

	@GetMapping("/{studentId}")
	public ResponseEntity<Student> viewStudent(@PathVariable("studentId") int studentId) {
		String END_POINT_URL = BASE_URL + studentId;
		Student student = restTemplate.getForObject(END_POINT_URL, Student.class);
		return ResponseEntity.ok(student);
	}

	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		ResponseEntity<Student> response = restTemplate.postForEntity(BASE_URL, student, Student.class);
		student = response.getBody();
		if(response.getStatusCode().is2xxSuccessful()){
			System.out.println("Operation that has to be performed only when we get a success response");
		}
		return ResponseEntity.ok(student);
	}

	@PutMapping
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		restTemplate.put(BASE_URL, student);
		String END_POINT_URL = BASE_URL + student.getStudentId();
		student = restTemplate.getForObject(END_POINT_URL, Student.class);
		return ResponseEntity.ok(student);
	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") int studentId) throws Exception{
		String END_POINT_URL = BASE_URL + studentId;
		restTemplate.delete(END_POINT_URL);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping
	public ResponseEntity<Student> patchStudent(@RequestBody Student student) {
		student = restTemplate.patchForObject(BASE_URL, student, Student.class);
		return ResponseEntity.ok(student);
	}

	@GetMapping
	public ResponseEntity<List<Student>> listStudents() {
		Student[] studentArray = restTemplate.getForObject(BASE_URL, Student[].class);
		List<Student> students = Arrays.asList(studentArray);
		return ResponseEntity.ok(students);
	}
	
//	@GetMapping  // For complex generic Types
//	public ResponseEntity<List<Student>> listStudents() {
//		ParameterizedTypeReference<List<Student>> reference = new ParameterizedTypeReference<List<Student>>() {};
//		ResponseEntity<List<Student>> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.GET, null,reference);
//		List<Student>  students = responseEntity.getBody();	
//	}

}
