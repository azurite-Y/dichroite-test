package com.zy.mapper;

import java.util.List;
import java.util.Map;

import org.zy.dichroite.fluorite.annotation.MapKey;
import org.zy.dichroite.fluorite.annotation.MapperMethod;

import com.zy.pojo.Teacher;

/**
 * @DateTime 2021年9月6日 下午3:11:53;
 * @author zy(azurite-Y);
 * @Description
 */
public interface TeacherMapper {
	@MapperMethod(value = "select * from teacher where tid = #{0}")
	Teacher selectByTid(Integer tid);
	
	@MapperMethod(value = "select * from teacher where tname = #{0}")
	List<Teacher> selectByTnameToList(String tname);
	
	@MapperMethod(value = "select * from teacher where tid in (#{ids})")
	@MapKey("tid")
	Map<String,Teacher> selectByTidToMap(Teacher teacher);
	
	@MapperMethod(value = "select * from teacher where tid in (#{0})")
	List<Teacher> selectByTidListToList(List<Integer> tids);
	
	@MapperMethod(value = "select * from teacher where tid = #{tid}")
	Teacher selectByMap(Map<String,?> map);

	@MapperMethod(value = "insert into teacher(tname,tid) value(#{tname},#{tid})")
	Integer insertTeacher(Teacher teacher);
	
	@MapperMethod(value = "delete from teacher where tid in (#{0})")
	Integer deleteByTidArray(Integer[] tids);
	
	@MapperMethod(value = "update teacher set tname = #{tname} where tid = #{tid})")
	Integer updateByTeacher(Teacher teacher);
	
	@MapperMethod(value = "update teacher set tname = #{0} where tid in (#{1})")
	Integer updateTeacher(String tname, List<Integer> ids);
}
