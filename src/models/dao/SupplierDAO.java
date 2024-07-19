package models.dao;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entities.ConfigEntity;
import entities.SupplierEntity;
import models.List.Configs;
import models.List.Suppliers;

@Transactional
public class SupplierDAO extends DAO{
	public SupplierDAO(SessionFactory factory) {
		super(factory);
		// TODO Auto-generated constructor stub
	}
	
	// SEARCH CATEGORY
		public Suppliers searchForSupplier(String keyword) {
			Session session = factory.openSession();
			String hql = "FROM SupplierEntity c WHERE c.name LIKE :keyword OR c.id LIKE:keyword OR c.description LIKE:keyword";
			Query query = session.createQuery(hql).setParameter("keyword", "%" + keyword + "%");
			List<SupplierEntity> list = query.setParameter("keyword", "%" + keyword + "%").list();
			Suppliers suppliers = new Suppliers(list);
			session.close();
			return suppliers;
		}
		
		// GET CONFIG FROM DB
		public Suppliers getSuppliers() throws IOException {
			Session session = factory.openSession();
			String hql = "FROM SupplierEntity";
			Query query = session.createQuery(hql);
			List<SupplierEntity> list = query.list();
			Suppliers suppliers = new Suppliers(list);
			System.out.println(suppliers + "PHUC");
			session.close();
			return suppliers;
		}
		
		// GET SINGLE CATEGORY
		public SupplierEntity getSupplier(String id) {
			Session session = factory.openSession();
			String hql = "FROM SupplierEntity WHERE id = '" + id + "'";
			Query query = session.createQuery(hql);
			SupplierEntity supplier = (SupplierEntity) query.list().get(0);
			session.close();
			return supplier;
		}
		
		// UPDATE CATEGORY IN DB
		public boolean updateSupplierInDB(SupplierEntity supplier) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.merge(supplier);
				t.commit();
				System.out.println("Supplier is udpated");
				return true;
			} catch (Exception e) {
				t.rollback();
				System.out.println("Error when updating Supplier");
				e.printStackTrace();
				return false;
			} finally {
				session.close();
			}
		}
		
		// ADD CONFIG TO DB
		public boolean addSupplierInDB(SupplierEntity supplier) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(supplier);
				t.commit();
				System.out.println("Supplier is added");
				return true;
			} catch (Exception e) {
				t.rollback();
				System.out.println("Error when adding Supplier");
				e.printStackTrace();
				return false;
			} finally {
				session.close();
			}
		}
		
		// DELETE CATEGORY IN DB
		public boolean deleteSupplierInDB(SupplierEntity supplier) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.delete(supplier);
				t.commit();
				System.out.println("Supplier is deleted");
				return true;
			} catch (Exception e) {
				t.rollback();
				System.out.println("Error when deleting Supplier");
				e.printStackTrace();
				return false;
			} finally {
				session.close();
			}
		}
		
}
