package com.li.stone.SpringBootDemo3.service;

import java.util.List;

import com.li.stone.SpringBootDemo3.domain.Student;

public interface StudentService {

	/**
	 * 增加学生
	 * @param student
	 * @return
	 */
	Student saveStudent(Student student);
	
	/**
	 * 删除单个学生
	 * @param id
	 * @return
	 */
	void removeStudent(Long id);
	
	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	Student updateStudent(Student student);
	
	/**
	 * 根据id获取学生信息
	 * @param id
	 * @return
	 */
	Student getStudentById(Long id);
	
	/**
	 * 获取学生列表
	 * @param user
	 * @return
	 */
	List<Student> listStudents();
	 
	/**
	 * 根据前台的姓名,性别,年龄进行查询学生的基本信息
	 * @param name
	 * @param sex
	 * @param age
	 * @return
	 */
	List<Student> listStudentsByNameAndAgeAndSex(String name,String sex,Integer age1,Integer age2);
	
}
