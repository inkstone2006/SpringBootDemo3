package com.li.stone.SpringBootDemo3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.li.stone.SpringBootDemo3.service.StudentService;
import com.li.stone.SpringBootDemo3.domain.Student;

import net.minidev.json.JSONArray;

@RestController//使用rest风格实现controller映射
@RequestMapping("/user")
public class StudentController {

	@Autowired
	StudentService studentService;
	/**
	 * 通过Get请求user,直接进入到这里的映射中
	 * @param model
	 * @return
	 */
	@GetMapping
	public ModelAndView queryAllStudent(Model model) {
	
		List<Student> students = studentService.listStudents();
		
		model.addAttribute("allStudent", students);
		model.addAttribute("title","学生信息表");
 		return new ModelAndView("index", "userModel", model);

	}
	
	
	/**
	 * 根据id查询学生的详细信息
	 */
	@GetMapping("{id}")//通过传递的是一个参数id进行的映射
	public ModelAndView view(@PathVariable("id") Long id,Model model){
		Student student = studentService.getStudentById(id);
		
		model.addAttribute("student",student);
		model.addAttribute("sex",student.getSex());
		model.addAttribute("title","查看学生");
		return new ModelAndView("view","userModel",model);
	}
	
	/**
	 * 返回学生新增界面
	 * @param model
	 * @return
	 */
	@RequestMapping("add")
	public ModelAndView addStudent(Model model) {
		model.addAttribute("title","新增");

		return new ModelAndView("add","userModel",model);
	}
	
//	/**
//	 * 新增学生
//	 * @param student
//	 * @return
//	 */
//	@RequestMapping("insert")
//	public ModelAndView insertStudent(HttpServletRequest req,Model model) {
//		String name = req.getParameter("name");
//		Integer age =Integer.parseInt(req.getParameter("age"));
//		String sex = req.getParameter("sex");
//		String[] course_ids = req.getParameterValues("course_id");
//		Integer course_id = Integer.parseInt(course_ids[0]);
//		Student student = new Student(name,sex,age,course_id);
//		 studentService.saveStudent(student);
//		return new ModelAndView("redirect:/user");//重新定向到index页面
//	}
	
	/**
	 * 这里直接定义Student对象,前端使用
	 * th:value="*{name}",和后端的对象直接进行了映射
	 * @param student
	 * @param model
	 * @return
	 */
	@RequestMapping("insert")
	public ModelAndView insertStudent(Student student) {
		 studentService.saveStudent(student);
		return new ModelAndView("redirect:/user");//重新定向到index页面
	}
	
	/**
	 * 学生信息的删除
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("del/{id}")
	public ModelAndView deleteStudent(@PathVariable("id") Long id,Model model) {
		studentService.removeStudent(id);
		return new ModelAndView("redirect:/user");//重新定向到index页面
			
	}
	
	/**
	 * 学生信息的修改
	 * @param student
	 * @return
	 */
	@PostMapping
	public ModelAndView updateStudent(Student student) {
		studentService.updateStudent(student);
		return new ModelAndView("redirect:/user");//重新定向到index页面

	}
	
	/**
	 * 根据姓名查询学生详细信息
	 * @param req
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@PostMapping("queryStudentByCondition")
	public void queryStudentByCondition(HttpServletRequest req,HttpServletResponse response) throws IOException {
		String name = req.getParameter("name");
		//防止前台的乱码问题
		response.setCharacterEncoding("utf-8");
		String sex = null;
		Integer age1 = null;
		Integer age2 = null;
		List<Student> students =  studentService.listStudentsByNameAndAgeAndSex(name, sex, age1, age2);

	    PrintWriter wirte;
	  //声明JSONArray对象并输入JSON字符串
        String array = JSONArray.toJSONString(students); 
        wirte = response.getWriter(); 
        wirte.print(array); 


	}
}
