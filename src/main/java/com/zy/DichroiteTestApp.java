package com.zy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zy.dichroite.autoconfigure.annotation.MapperScanner;
import org.zy.fluorite.autoconfigure.transaction.EnableTransactionManagement;
import org.zy.fluorite.boot.FluoriteApplication;
import org.zy.fluorite.boot.annotation.RunnerAs;
import org.zy.fluorite.context.interfaces.ConfigurableApplicationContext;
import org.zy.fluorite.core.annotation.Autowired;

import com.zy.mapper.StudentMapper;
import com.zy.mapper.TeacherMapper;
import com.zy.pojo.Student;
import com.zy.pojo.Teacher;

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
	
	public static void main(String[] args) {
		String[] arr = {};
		ConfigurableApplicationContext run = FluoriteApplication.run(DichroiteTestApp.class, arr);
		DichroiteTestApp dichroiteTestApp = run.getBean(DichroiteTestApp.class);
		dichroiteTestApp.selectByMap();
	}
	
	//---------------------------------------------StudentMapper------------------------------------------
	public void insertStudent() {
		Student student = new Student();
		student.setId(15);
		student.setName("insertStudent");
		student.setSex(0);
		Integer updateCount = this.studentMapper.insertStudent(student);
		System.out.println(updateCount);
	}
	
	public void updateByStudent() {
		Student student = new Student();
		student.setId(15);
		Integer updateCount = this.studentMapper.deleteByStudent(student);
		System.out.println(updateCount);
	}
	
	public void selectByStudentPojo() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 16; i++) {
			list.add(i);
		}
		Student student = new Student();
		student.setLists(list);
		
		List<Student> selectByPojo = this.studentMapper.selectByPojo(student);
		selectByPojo.forEach(System.out::println);
	}
	
	public void deleteByStudent() {
		Student student = new Student();
		student.setId(15);
		Integer updateCount = this.studentMapper.deleteByStudent(student);
		System.out.println(updateCount);
	}
	
	//---------------------------------------------TeacherMapper------------------------------------------
	public void selectByTid() {
		Teacher teacher = this.teacherMapper.selectByTid(1);
		System.out.println(teacher);
	}
	
	public void selectByTnameToList() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 16; i++) {
			list.add(i);
		}
		List<Teacher> teachers = this.teacherMapper.selectByTidListToList(list);
		teachers.forEach(System.out::println);
	}
	
	public void selectByMap() {
		Map<String,Integer> map = new HashMap<>();
		map.put("tid", 1);
		Teacher teacher = this.teacherMapper.selectByMap(map);
		System.out.println(teacher);
	}
	
	public void selectByTidToMap() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 16; i++) {
			list.add(i);
		}
		Teacher teacher = new Teacher();
		teacher.setIds(list);
		Map<String,Teacher> teachers = this.teacherMapper.selectByTidToMap(teacher);
		teachers.forEach((key, value) -> {
			System.out.println("key:" + key +"\t value:" + value);
		});
	}
	
	public void selectByTidListToList() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			list.add(i);
		}
		List<Teacher> teachers = this.teacherMapper.selectByTidListToList(list);
		teachers.forEach(System.out::println);

	}
	
	public void insertTeacher() {
		Integer insertTeacher = this.teacherMapper.insertTeacher(new Teacher(1, "insertTeacher"));
		System.out.println(insertTeacher);
	}
	
	public void updateTeacher(DichroiteTestApp dichroiteTestApp) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			list.add(i);
		}
		Integer updateTeacher = this.teacherMapper.updateTeacher("updateTeacher", list);
		System.out.println(updateTeacher);
	}
	
	public void updateByTeacher() {
		Integer updateCount = this.teacherMapper.updateByTeacher(new Teacher(3, "admin2"));
		System.out.println(updateCount);
	}
	
	public void deleteByTidArray() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			list.add(i);
		}
		Integer[] arr = {};
		Integer updateCount = this.teacherMapper.deleteByTidArray(list.toArray(arr));
		System.out.println(updateCount);
	}
}
