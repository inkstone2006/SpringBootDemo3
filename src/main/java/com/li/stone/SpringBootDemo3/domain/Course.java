package com.li.stone.SpringBootDemo3.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * 课程类
 * @author lenovo
 *
 */
@Entity
public class Course implements Serializable{//为了前台网络的传输,需要实现这个序列化接口

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//表示其增长策略为自增长
	private Long id;//课程号号唯一标识

	@NotEmpty(message = "课程名不能为空")
	@Size(min=2, max=20)//设置数据库对应的字段的取值范围
	@Column(nullable = false, length = 20) // 映射为字段，值不能为空	
	private String name;//课程名
	

	protected Course() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Course(String name) {
		super();
		this.name = name;
	}
	
	
}
