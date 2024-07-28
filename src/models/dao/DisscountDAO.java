package models.dao;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entities.ConfigEntity;
import entities.DisscountEntity;

import models.List.Disscounts;

@Transactional
public class DisscountDAO extends DAO{
	public DisscountDAO(SessionFactory factory) {
		super(factory);
		// TODO Auto-generated constructor stub
	}
	
	// Search Disscount
	public Disscounts searchForDisscounts(String keyword) {
		Session session = factory.openSession();
		String hql = "FROM DisscountEntity c WHERE c.name LIKE :keyword OR c.id LIKE:keyword OR c.description LIKE:keyword";
		Query query = session.createQuery(hql).setParameter("keyword", "%" + keyword + "%");
		List<DisscountEntity> list = query.setParameter("keyword", "%" + keyword + "%").list();
		Disscounts disscounts = new Disscounts(list);
		session.close();
		return disscounts;
	}
	
	// get lisst disscount
	public Disscounts getDisscounts() throws IOException {
		Session session = factory.openSession();
		String hql = "FROM DisscountEntity";
		Query query = session.createQuery(hql);
		List<DisscountEntity> list = query.list();
		Disscounts disscounts = new Disscounts(list);
		session.close();
		return disscounts;
	}
	
	// get single disscount
	public DisscountEntity getDisscount(String id) {
		Session session = factory.openSession();
		String hql = "FROM ConfigEntity WHERE id = '" + id + "'";
		Query query = session.createQuery(hql);
		DisscountEntity disscount = (DisscountEntity) query.list().get(0);
		session.close();
		return disscount;
	}
	
	// UPDATE disscount IN DB
	public boolean updateDisscountInDB(DisscountEntity disscount) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.merge(disscount);
			t.commit();
			System.out.println("disscount is udpated");
			return true;
		} catch (Exception e) {
			t.rollback();
			System.out.println("Error when updating disscount");
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}	

// ADD CONFIG TO DB
	public boolean addDisscountInDB(DisscountEntity disscount) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(disscount);
			t.commit();
			System.out.println("disscount is added");
			return true;
		} catch (Exception e) {
			t.rollback();
			System.out.println("Error when adding disscount");
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	// DELETE disscount IN DB
	public boolean deleteDisscountInDB(DisscountEntity disscount) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(disscount);
			t.commit();
			System.out.println("disscount is deleted");
			return true;
		} catch (Exception e) {
			t.rollback();
			System.out.println("Error when deleting disscount");
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
}
