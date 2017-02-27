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
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		// 创建sessionfactory
		SessionFactory sf = config.buildSessionFactory(serviceRegistry);
		// 创建session对象
		Session session = sf.getCurrentSession();
		// 创建schemaexport对象
		SchemaExport export = new SchemaExport(config);
		export.create(true, true);

	}

	@Test
	public void testStudentSave() {
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		// 创建sessionfactory
		SessionFactory sf = config.buildSessionFactory(serviceRegistry);
		// 创建session对象
		Session session = sf.getCurrentSession();
		//创建事物
		Transaction tx=session.beginTransaction();
		Students s1=new Students("s0000001", "离校", "男", new Date(), "山西");
		Students s2=new Students("s0000002", "小米", "女", new Date(), "山西");
		Students s3=new Students("s0000003", "老王", "男", new Date(), "山西");
		session.save(s1);
		session.save(s2);
		session.save(s3);
		
		//提交事物
		tx.commit();
		//关闭session
		sf.close();

	}
}
