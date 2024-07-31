package com.techietact.tutorial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.techietact.tutorial.model.Student;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class RestTemplateImpl implements StudentService{

	@Value("${other.service.url}")
	private String BASE_URL;

	private final RestTemplate restTemplate;
	
	@Override
	public Student createStudent(Student student) {
		ResponseEntity<Student> response = restTemplate.postForEntity(BASE_URL, student, Student.class);
		student = response.getBody();
		if(response.getStatusCode().is2xxSuccessful()){
			System.out.println("Operation that has to be performed only when we get a success response");
		}
		return student;
	}

	@Override
	public Student viewStudent(int studentId) {
		String END_POINT_URL = BASE_URL + studentId;
		Student student = restTemplate.getForObject(END_POINT_URL, Student.class);
		return student;
	}

	@Override
	public Student updateStudent(Student student) {
		restTemplate.put(BASE_URL, student);
		String END_POINT_URL = BASE_URL + student.getStudentId();
		student = restTemplate.getForObject(END_POINT_URL, Student.class);
		return student;
	}

	@Override
	public Student partialUpdateStudent(Student student) {
		student = restTemplate.patchForObject(BASE_URL, student, Student.class);
		return student;
	}

	@Override
	public void deleteStudent(int studentId) {
		String END_POINT_URL = BASE_URL + studentId;
		restTemplate.delete(END_POINT_URL);
	}

	@Override
	public List<Student> listStudents() {
		
		ParameterizedTypeReference<List<Student>> reference = new ParameterizedTypeReference<List<Student>>() {};
		ResponseEntity<List<Student>> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.GET, null,reference);
		List<Student>  students = responseEntity.getBody();	 
		
//		Student[] studentArray = restTemplate.getForObject(BASE_URL, Student[].class);
//		List<Student> students = Arrays.asList(studentArray);
		return students;
	}

}
