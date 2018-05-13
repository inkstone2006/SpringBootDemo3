package com.li.stone.SpringBootDemo3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.li.stone.SpringBootDemo3.domain.Student;

/**
 * 使用JPA自定义接口进行实现,少些代码
 * 这个例子在原有的接口方法上多定义了一些方法,以便应用
 * @author lenovo
 *
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

	//根据方法名实现底层自动化生成sql,根据学生性别查询学生信息
	List<Student> findBySex(String sex);
	
	//根据学生的姓模糊查询学生的详细信息,该方法名等同于where name like ?1; 
	@Query("select s from Student s where name like CONCAT('%',:name,'%')")
	List<Student> findByNameLike(@Param("name") String name);
	
	//根据学生年龄段查询学生的详细信息
	List<Student> findByAgeBetween(Integer age1,Integer age2);
	
	//如果感觉JPA不能满足需求也可以自定义sql;注意这里占位符?后面是数字1,select后面不能使用*,必须要给表取个别名,否则会报错
	@Query("select s from Student s where s.name =?1")
	List<Student>  getStudent();
}
