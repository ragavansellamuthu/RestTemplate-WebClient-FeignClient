package com.techietact.tutorial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.techietact.tutorial.model.Student;

import lombok.RequiredArgsConstructor;

//@Primary
@Service
@RequiredArgsConstructor
public class WebClientImpl implements StudentService {

	@Value("${other.service.url}")
	private String BASE_URL;
	
	private final WebClient.Builder builder  ;
	
	@Override
	public Student createStudent(Student student) {
		student = builder.build().post().uri(BASE_URL).bodyValue(student).retrieve().bodyToMono(Student.class).block();
		return student;
	}

	@Override
	public Student viewStudent(int studentId) {
		String END_POINT_URL = BASE_URL + studentId;
		Student student = builder.build().get().uri(END_POINT_URL).retrieve().bodyToMono(Student.class).block();
		return student;
	}

	@Override
	public Student updateStudent(Student student) {
		student = builder.build().put().uri(BASE_URL).bodyValue(student).retrieve().bodyToMono(Student.class).block();
		return student;
	}

	@Override
	public Student partialUpdateStudent(Student student) {
		student = builder.build().patch().uri(BASE_URL).bodyValue(student).retrieve().bodyToMono(Student.class).block();
		return student;
	}

	@Override
	public void deleteStudent(int studentId) {
		String END_POINT_URL = BASE_URL + studentId;
		builder.build().delete().uri(END_POINT_URL).retrieve().bodyToMono(Void.class);
	}

	@Override
	public List<Student> listStudents() {
		List<Student> students = builder.build().get().uri(BASE_URL).retrieve().bodyToFlux(Student.class).collectList().block();
		return students;
	}

}
