package models.dao;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entities.CategoryEntity;
import entities.ConfigEntity;
import models.BcryptEncryption;
import models.List.Categories;
import models.List.Configs;
import net.sf.ehcache.hibernate.HibernateUtil;

@Transactional
public class ConfigDAO extends DAO {
	public ConfigDAO(SessionFactory factory) {
		super(factory);
		// TODO Auto-generated constructor stub
	}
	
	// SEARCH CATEGORY
		public Configs searchForConfig(String keyword) {
			Session session = factory.openSession();
			String hql = "FROM ConfigEntity c WHERE c.name LIKE :keyword OR c.id LIKE:keyword OR c.description LIKE:keyword";
			Query query = session.createQuery(hql).setParameter("keyword", "%" + keyword + "%");
			List<ConfigEntity> list = query.setParameter("keyword", "%" + keyword + "%").list();
			Configs configs = new Configs(list);
			session.close();
			return configs;
		}
	
	// GET CONFIG FROM DB
	public Configs getConfigs() throws IOException {
		Session session = factory.openSession();
		String hql = "FROM ConfigEntity";
		Query query = session.createQuery(hql);
		List<ConfigEntity> list = query.list();
		Configs configs = new Configs(list);
//		System.out.println(configs + "PHUC");
		session.close();
		return configs;
	}
	
	// GET SINGLE CATEGORY
		public ConfigEntity getConfig(String id) {
			Session session = factory.openSession();
			String hql = "FROM ConfigEntity WHERE id = '" + id + "'";
			Query query = session.createQuery(hql);
			ConfigEntity config = (ConfigEntity) query.list().get(0);
			session.close();
			return config;
		}
		
		// UPDATE CATEGORY IN DB
		public boolean updateConfigInDB(ConfigEntity config) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.merge(config);
				t.commit();
				System.out.println("Config is udpated");
				return true;
			} catch (Exception e) {
				t.rollback();
				System.out.println("Error when updating config");
				e.printStackTrace();
				return false;
			} finally {
				session.close();
			}
		}
		
		// ADD CONFIG TO DB
		public boolean addConfigInDB(ConfigEntity config) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(config);
				t.commit();
				System.out.println("Config is added");
				return true;
			} catch (Exception e) {
				t.rollback();
				System.out.println("Error when adding config");
				e.printStackTrace();
				return false;
			} finally {
				session.close();
			}
		}
		
		// DELETE CATEGORY IN DB
		public boolean deleteConfigInDB(ConfigEntity config) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.delete(config);
				t.commit();
				System.out.println("Config is deleted");
				return true;
			} catch (Exception e) {
				t.rollback();
				System.out.println("Error when deleting config");
				e.printStackTrace();
				return false;
			} finally {
				session.close();
			}
		}
	
}
