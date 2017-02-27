package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import entity.Students;
import service.StudentsDao;
import service.impl.StudentsDaoImpl;

public class StudentsAction extends SuperAction implements ModelDriven<Students> {

	private Students s=new Students();
	//查询所有学生动作
	public String queryAllStudents(){
		StudentsDao sdao=new StudentsDaoImpl();
		List<Students> list=sdao.queryAllStudents();
		if(list!=null&&list.size()>0){
			//放进session中
			session .setAttribute("students_list", list);
		}
		return "students_success";
	}
	//根据学生姓名查询动作
	public String selectStudents(){
		StudentsDao sdao=new StudentsDaoImpl();
		String sname=request.getParameter("sname");
		List<Students> list=sdao.selectStudentBySname(sname);
		if(list!=null&&list.size()>0){
			//放进session中
			session .setAttribute("select_list", list);
		}
		return "select_success";
	}
	//删除学生动作
	public String deleteStudents(){
		StudentsDao sdao=new StudentsDaoImpl();
		String sid=request.getParameter("sid");
		sdao.deleteStudents(sid);
		return "delete_success";
	}
	//修改学生动作
	public String modify(){
		StudentsDao sdao=new StudentsDaoImpl();
		//获得学生编号
		String sid=request.getParameter("sid");
		Students s=sdao.queryStudentsById(sid);
		//保存在回话中
		session.setAttribute("modify_students", s);
		return "modify_success";
	}
	//修改学生后的保存动作
	public String update() throws Exception{
		StudentsDao sdao=new StudentsDaoImpl();
		Students s=new Students();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		s.setAddress(request.getParameter("address"));
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
		s.setBirthday(sf.parse(request.getParameter("birthday")));
		sdao.updateStudents(s);
		return "update_success";
	}
	
	//添加学生动作
	/*public String addStudents() throws Exception{
		Students s=new Students();
		StudentsDao sdao=new StudentsDaoImpl();
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		s.setAddress(request.getParameter("address"));
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
		s.setBirthday(sf.parse(request.getParameter("birthday")));
		sdao.addStudents(s);
		return "add_success";
	}*/
	
	//使用模型驱动
	public String addStudents(){
		
		StudentsDao sdao=new StudentsDaoImpl();
		sdao.addStudents(s);
		return "add_success";
	}
	public Students getModel() {
		// TODO Auto-generated method stub
		return s;
	}
}
