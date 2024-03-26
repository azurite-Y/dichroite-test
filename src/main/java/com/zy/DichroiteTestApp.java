package com.zy;

import com.zy.mapper.StudentMapper;
import com.zy.mapper.TeacherMapper;
import com.zy.pojo.Student;
import com.zy.pojo.Teacher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zy.dichroite.autoconfigure.annotation.MapperScanner;
import org.zy.fluorite.autoconfigure.transaction.EnableTransactionManagement;
import org.zy.fluorite.boot.FluoriteApplication;
import org.zy.fluorite.boot.annotation.RunnerAs;
import org.zy.fluorite.context.interfaces.ConfigurableApplicationContext;
import org.zy.fluorite.core.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @DateTime 2021年9月6日 上午11:34:23;
 * @author zy(azurite-Y);
 * @Description
 */
@RunnerAs(debug = false, debugFormAop = false, debugFromTransaction = false)
@EnableTransactionManagement
@MapperScanner("com.zy.mapper")
public class DichroiteTestApp {
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	
	private DichroiteTestApp dichroiteTestApp;
	
	
	@BeforeEach
	public void beforeEach() {
		ConfigurableApplicationContext run = FluoriteApplication.run(DichroiteTestApp.class, new String[]{});
		dichroiteTestApp = run.getBean(DichroiteTestApp.class);
	}
	
	
	//---------------------------------------------StudentMapper------------------------------------------
	@Test
	public void insertStudent() {
		Student student = new Student();
		student.setId(15);
		student.setName("insertStudent");
		student.setSex(0);
		
		/*
		 * Preparing: insert into student(id,name,sex) values(?,?,?)
		 * Parameters: [15, insertStudent, 0]
		 * result: 1
		 */
		Integer updateCount = dichroiteTestApp.studentMapper.insertStudent(student);
		System.out.println(updateCount);
	}
	
	@Test
	public void updateByStudent() {
		Student student = new Student();
		student.setId(15);
		student.setSex(1);
		
		/*
		 * Preparing: update student set sex=? where id=?
		 * Parameters: [1, 15]
		 * result: 1
		 */
		Integer updateCount = dichroiteTestApp.studentMapper.updateByStudent(student);
		System.out.println(updateCount);
	}
	
	@Test
	public void selectByStudentPojo() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 16; i++) {
			list.add(i);
		}
		Student student = new Student();
		student.setLists(list);
		
		/*
		 * Preparing: select * from student where  id in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
		 * Parameters: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
		 * retult: Student [id=15, name=insertStudent, sex=1, lists=null]
		 */
		List<Student> selectByPojo = dichroiteTestApp.studentMapper.selectByPojo(student);
		selectByPojo.forEach(System.out::println);
	}
	
	@Test
	public void deleteByStudent() {
		Student student = new Student();
		student.setId(15);
		
		/*
		 * Preparing: delete from student where  id=?
		 * Parameters: [15]
		 * result: 1
		 */
		Integer updateCount = dichroiteTestApp.studentMapper.deleteByStudent(student);
		System.out.println(updateCount);
	}
	
	
	//---------------------------------------------TeacherMapper------------------------------------------
	@Test
	public void insertTeacher() {
		/*
		 * Preparing: insert into teacher(tname,tid) values(?,?)
		 * Parameters: [insertTeacher, 1]
		 * result: 1
		 */
		Integer insertTeacher = dichroiteTestApp.teacherMapper.insertTeacher(new Teacher(13, "insertTeacher"));
		System.out.println(insertTeacher);
	}
	
	@Test
	public void updateTeacher() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			list.add(i);
		}
		
		/*
		 * Preparing: update teacher set tname = ? where tid in (?,?,?,?,?)
		 * Parameters: [updateTeacher, 1, 2, 3, 4, 5]
		 * result: 1
		 */
		Integer updateTeacher = dichroiteTestApp.teacherMapper.updateTeacher("updateTeacher", list);
		System.out.println(updateTeacher);
	}
	
	@Test
	public void updateByTeacher() {
		/*
		 * Preparing: update teacher set tname = ? where tid = ?
		 * Parameters: [admin2, 3]
		 * result: 1
		 */
		Integer updateCount = dichroiteTestApp.teacherMapper.updateByTeacher(new Teacher(3, "admin2"));
		System.out.println(updateCount);
	}
	
	@Test
	public void selectByTid() {
		/*
		 * Preparing: select * from teacher where tid = ?
		 * Parameters: [1]
		 * result: Teacher [tid=1, tname=insertTeacher, ids=null
		 */
		Teacher teacher = dichroiteTestApp.teacherMapper.selectByTid(1);
		System.out.println(teacher);
	}
	
	@Test
	public void selectByTnameToList() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 16; i++) {
			list.add(i);
		}
		
		/*
		 * Preparing: select * from teacher where tid in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
		 * Parameters: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
		 * result:
		 * 	--> Teacher [tid=1, tname=insertTeacher, ids=null]
		 */
		List<Teacher> teachers = dichroiteTestApp.teacherMapper.selectByTidListToList(list);
		teachers.forEach(System.out::println);
	}
	
	@Test
	public void selectByMap() {
		Map<String,Integer> map = new HashMap<>();
		map.put("tid", 1);
		
		/*
		 * Preparing: select * from teacher where tid = ?
		 * Parameters: [1]
		 * result: Teacher [tid=1, tname=insertTeacher, ids=null]
		 */
		Teacher teacher = dichroiteTestApp.teacherMapper.selectByMap(map);
		System.out.println(teacher);
	}
	
	@Test
	public void selectByTidToMap() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 16; i++) {
			list.add(i);
		}
		Teacher teacher = new Teacher();
		teacher.setIds(list);
		
		/*
		 * Preparing: select * from teacher where tid in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
		 * Parameters: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
		 * result:
		 *   --> key:1	 value:Teacher [tid=1, tname=insertTeacher, ids=null]
		 */
		Map<String,Teacher> teachers = dichroiteTestApp.teacherMapper.selectByTidToMap(teacher);
		teachers.forEach((key, value) -> {
			System.out.println("key:" + key +"\t value:" + value);
		});
	}
	
	@Test
	public void selectByTidListToList() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			list.add(i);
		}
		
		/*
		 * Preparing: select * from teacher where tid in (?,?,?,?,?)
		 * Parameters: [1, 2, 3, 4, 5]
		 * result:
		 *   --> Teacher [tid=1, tname=insertTeacher, ids=null]
		 */
		List<Teacher> teachers = dichroiteTestApp.teacherMapper.selectByTidListToList(list);
		teachers.forEach(System.out::println);
	}
	
	@Test
	public void deleteByTidArray() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 3; i++) {
			list.add(i);
		}
		Integer[] arr = {};
		
		/*
		 * Preparing: delete from teacher where tid in (?,?)
		 * Parameters: [1, 2]
		 * result: 1
		 */
		Integer updateCount = dichroiteTestApp.teacherMapper.deleteByTidArray(list.toArray(arr));
		System.out.println(updateCount);
	}
}
