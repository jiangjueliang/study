package entity;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class TestStudents {
	@Test
	public void testSchemaExport() {
		// �������ö���
		Configuration config = new Configuration().configure();
		// �����������
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		// ����sessionfactory
		SessionFactory sf = config.buildSessionFactory(serviceRegistry);
		// ����session����
		Session session = sf.getCurrentSession();
		// ����schemaexport����
		SchemaExport export = new SchemaExport(config);
		export.create(true, true);

	}

	@Test
	public void testStudentSave() {
		// �������ö���
		Configuration config = new Configuration().configure();
		// �����������
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		// ����sessionfactory
		SessionFactory sf = config.buildSessionFactory(serviceRegistry);
		// ����session����
		Session session = sf.getCurrentSession();
		//��������
		Transaction tx=session.beginTransaction();
		Students s1=new Students("s0000001", "��У", "��", new Date(), "ɽ��");
		Students s2=new Students("s0000002", "С��", "Ů", new Date(), "ɽ��");
		Students s3=new Students("s0000003", "����", "��", new Date(), "ɽ��");
		session.save(s1);
		session.save(s2);
		session.save(s3);
		
		//�ύ����
		tx.commit();
		//�ر�session
		sf.close();

	}
}
