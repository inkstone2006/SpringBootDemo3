package com.li.stone.SpringBootDemo3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.li.stone.SpringBootDemo3.repository.StudentRepository;
import com.li.stone.SpringBootDemo3.domain.Student;
@Service
public class StudentServiceImpl implements StudentService{

	
	//注入StudentRepositoryJPA
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
	
		return studentRepository.save(student);
	}

	@Override
	public void removeStudent(Long id) {
		// TODO Auto-generated method stub
		 studentRepository.deleteById(id);
	}

	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).get();
	}

	@Override
	public List<Student> listStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public List<Student> listStudentsByNameAndAgeAndSex(String name, String sex,Integer age1,Integer age2) {
		// TODO Auto-generated method stub
		if(name != null) {
			return studentRepository.findByNameLike(name);
		}else if(sex != null) {
			return studentRepository.findBySex(sex);
		}else {
			return studentRepository.findByAgeBetween(age1,age2);
		}
	
	}
	

}
