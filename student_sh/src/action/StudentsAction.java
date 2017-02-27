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
	//��ѯ����ѧ������
	public String queryAllStudents(){
		StudentsDao sdao=new StudentsDaoImpl();
		List<Students> list=sdao.queryAllStudents();
		if(list!=null&&list.size()>0){
			//�Ž�session��
			session .setAttribute("students_list", list);
		}
		return "students_success";
	}
	//����ѧ��������ѯ����
	public String selectStudents(){
		StudentsDao sdao=new StudentsDaoImpl();
		String sname=request.getParameter("sname");
		List<Students> list=sdao.selectStudentBySname(sname);
		if(list!=null&&list.size()>0){
			//�Ž�session��
			session .setAttribute("select_list", list);
		}
		return "select_success";
	}
	//ɾ��ѧ������
	public String deleteStudents(){
		StudentsDao sdao=new StudentsDaoImpl();
		String sid=request.getParameter("sid");
		sdao.deleteStudents(sid);
		return "delete_success";
	}
	//�޸�ѧ������
	public String modify(){
		StudentsDao sdao=new StudentsDaoImpl();
		//���ѧ�����
		String sid=request.getParameter("sid");
		Students s=sdao.queryStudentsById(sid);
		//�����ڻػ���
		session.setAttribute("modify_students", s);
		return "modify_success";
	}
	//�޸�ѧ����ı��涯��
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
	
	//���ѧ������
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
	
	//ʹ��ģ������
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
