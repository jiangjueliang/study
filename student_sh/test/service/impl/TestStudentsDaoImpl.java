package service.impl;

import java.util.List;

import org.junit.Test;

import entity.Students;
import service.StudentsDao;

public class TestStudentsDaoImpl {

	@Test
	public void testQueryAllStudents(){
		StudentsDao sdao=new StudentsDaoImpl();
		List<Students> list=sdao.queryAllStudents();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	@Test
	public void testGetNewSid(){
		StudentsDaoImpl sdao=new StudentsDaoImpl();
		//System.out.println(sdao.getNewSid());
	}
}
