package com.techietact.tutorial.service;

import java.util.List;

import com.techietact.tutorial.model.Student;

public interface StudentService {

	Student createStudent(Student student);
	
	Student viewStudent(int studentId);
	
	Student updateStudent(Student student);
	
	Student partialUpdateStudent(Student student);
	
	void deleteStudent(int studentId);
	
	List<Student> listStudents();
	
}
