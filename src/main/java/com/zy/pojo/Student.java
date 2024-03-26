package com.zy.pojo;

import java.io.Serializable;
import java.util.List;

import org.zy.dichroite.fluorite.annotation.BeanMapping;
import org.zy.dichroite.fluorite.annotation.Column;

/**
 * 应用于动态 SQL 的 JavaBean
 * @author Azurite
 *
 */
@BeanMapping
public class Student implements Serializable {
	private static final long serialVersionUID = -8378578783782189563L;

	@Column(name="id",level = 1)
	private Integer id;

	@Column(name = "name", level = 2)
	private String name;

	@Column(name = "sex", level = 2)
	private Integer sex;
	
	@Column(name = "id")
	private List<Integer> lists;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public List<Integer> getLists() {
		return lists;
	}

	public void setLists(List<Integer> lists) {
		this.lists = lists;
	}

	public Student(Integer id, String name, Integer sex, List<Integer> lists) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.lists = lists;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", lists=" + lists + "]";
	}

	public Student() {
		super();
	}
}
