package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;

import entity.Students;
import service.StudentsDao;

public class StudentsDaoImpl implements StudentsDao {

	public List<Students> queryAllStudents() {
		Session session=null;
		String hql="";
		Transaction tx=null;
		List<Students> list=null;
		try {
			session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			hql="from Students";
			Query query=session.createQuery(hql);
			list=query.list();
			tx.commit();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			return list;
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
	}

	public Students queryStudentsById(String sid) {
		Session session=null;
		Transaction tx=null;
		Students s=null;
		try {
			session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			s=(Students) session.get(Students.class, sid);
			tx.commit();
			return s;
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			return s;
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
	}

	public boolean addStudents(Students s) {
		Transaction tx=null;
		Session session=null;
		s.setSid(getNewSid());//设置学生的编号
		try {
			session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			session.save(s);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
	}

	public boolean updateStudents(Students s) {
		Transaction tx=null;
		Session session=null;
		try {
			session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			session.update(s);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
	}

	public boolean deleteStudents(String sid) {
		Transaction tx=null;
		String hql="";
		Session session=null;
		try {
			session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			Students student=(Students) session.get(Students.class, sid);
			session.delete(student);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
	}
	//生成学生主键方法
	private String getNewSid(){
		Transaction tx=null;
		String hql="";
		Session session=null;
		String sid="";
		try {
			session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			//得到当前学生的最大编号
			hql="select max(sid) from Students";
			Query query=session.createQuery(hql);
			sid=(String) query.uniqueResult();
			if(sid==null||sid.equals("")){
				sid="s0000001";
			}else{
				String temp=sid.substring(1);
				int i=Integer.parseInt(temp);
				i++;
				//还原为字符串
				temp=String.valueOf(i);
				int len=temp.length();
				for(int j=0;j<7-len;j++){
					temp="0"+temp;
				}
				sid="s"+temp;
			}
			tx.commit();
			return sid;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return null;
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
	}



	public List<Students> selectStudentBySname(String sname) {
		Session session=null;
		String hql="";
		Transaction tx=null;
		List<Students> selectlist=null;
		try {
			session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			hql="from Students where sname like '%"+sname+"%'";
			Query query=session.createQuery(hql);
			selectlist=query.list();
			tx.commit();
			return selectlist;
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			return selectlist;
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
	}

}
