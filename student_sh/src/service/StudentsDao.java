package service;

import java.util.List;

import entity.Students;

//学生业务接口
public interface StudentsDao {

	//查询所有学生
	public List<Students> queryAllStudents();
	
	//根据学生编号查询学生
	public Students queryStudentsById(String sid);
	
	//添加学生资料
	public boolean addStudents(Students s);
	
	//修改学生资料
	public boolean updateStudents(Students s);
	
	//删除学生资料
	public boolean deleteStudents(String sid); 
}
