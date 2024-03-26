package com.zy.mapper;

import java.util.List;

import org.zy.dichroite.fluorite.annotation.InsertValue;
import org.zy.dichroite.fluorite.annotation.MapperMethod;
import org.zy.dichroite.fluorite.annotation.QueryTemplate;
import org.zy.dichroite.fluorite.annotation.QueryWhere;
import org.zy.dichroite.fluorite.annotation.UpdateSet;
import org.zy.dichroite.fluorite.mapping.SqlCommandType;
import org.zy.fluorite.transaction.annotation.Transactional;

import com.zy.pojo.Student;

/**
 * @DateTime 2021年9月6日 下午3:11:46;
 * @author zy(azurite-Y);
 * @Description 动态sql测试
 */
public interface StudentMapper {
	@MapperMethod(value = "select * from student where &x ")
	@QueryWhere(name = "&x",mode = QueryTemplate.parallel)
	List<Student> selectByPojo(Student student);

	@MapperMethod(value = "insert into student(&x) values(&y)", sqlCommandType = SqlCommandType.INSERT)
	@InsertValue(name = "&x", value = "&y")
	@Transactional
	Integer insertStudent(Student student);
	
	@MapperMethod(value = "delete from student where &x", sqlCommandType = SqlCommandType.DELETE)
	@QueryWhere(name = "&x")
	@Transactional
	Integer deleteByStudent(Student student);
	
	@MapperMethod(value = "update student set &x where &y", sqlCommandType = SqlCommandType.UPDATE)
	@QueryWhere(name = "&y")
	@UpdateSet(name = "&x")
	@Transactional
	Integer updateByStudent(Student student);
}
