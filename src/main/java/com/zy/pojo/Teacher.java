package com.zy.pojo;

import java.io.Serializable;
import java.util.List;

import org.zy.dichroite.fluorite.annotation.BeanMapping;

@SuppressWarnings("serial")
@BeanMapping
public class Teacher implements Serializable {
	private int tid;
	private String tname;
	private List<Integer> ids;

	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	public Teacher() {
		super();
	}
	public Teacher(int tid, String tname) {
		super();
		this.tid = tid;
		this.tname = tname;
	}
	public Teacher(int tid, String tname, List<Integer> ids) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.ids = ids;
	}
	
	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", tname=" + tname + ", ids=" + ids + "]";
	}
}
