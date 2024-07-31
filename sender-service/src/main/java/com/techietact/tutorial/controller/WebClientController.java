package com.techietact.tutorial.controller;

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
import org.springframework.web.reactive.function.client.WebClient;

import com.techietact.tutorial.model.Student;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wc")
public class WebClientController {

	private final WebClient.Builder builder  ;
	

	@Value("${other.service.url}")
	private String BASE_URL;

	@GetMapping("/{studentId}")
	public ResponseEntity<Student> viewStudent(@PathVariable("studentId") int studentId) {
		String END_POINT_URL = BASE_URL + studentId;
		Student student = builder.build().get().uri(END_POINT_URL).retrieve().bodyToMono(Student.class).block();
		return ResponseEntity.ok(student);
	}

	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		student = builder.build().post().uri(BASE_URL).bodyValue(student).retrieve().bodyToMono(Student.class).block();
		return ResponseEntity.ok(student);
	}

	@PutMapping
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		student = builder.build().put().uri(BASE_URL).bodyValue(student).retrieve().bodyToMono(Student.class).block();
		return ResponseEntity.ok(student);
	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") int studentId) throws Exception{
		String END_POINT_URL = BASE_URL + studentId;
		builder.build().delete().uri(END_POINT_URL).retrieve().bodyToMono(Void.class);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping
	public ResponseEntity<Student> patchStudent(@RequestBody Student student) {
		student = builder.build().patch().uri(BASE_URL).bodyValue(student).retrieve().bodyToMono(Student.class).block();
		return ResponseEntity.ok(student);
	}

	@GetMapping
	public ResponseEntity<List<Student>> listStudents() {
		List<Student> students = builder.build().get().uri(BASE_URL).retrieve().bodyToFlux(Student.class).collectList().block();
		return ResponseEntity.ok(students);
	}
	
}
