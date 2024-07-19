package models.List;

import java.util.ArrayList;
import java.util.List;

import entities.ConfigEntity;
import entities.SupplierEntity;

public class Suppliers {
	private List<SupplierEntity> list;

	public Suppliers() {
		// TODO Auto-generated constructor stub
		list = new ArrayList<SupplierEntity>();
	}


	public List<SupplierEntity> getList() {
		return list;
	}

	public void setList(List<SupplierEntity> suppliers) {
		this.list = suppliers;
	}
	
	public Suppliers(List<SupplierEntity> suppliers) {
		super();
		this.list = suppliers;
	}
}
