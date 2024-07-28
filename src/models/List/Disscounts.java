package models.List;

import java.util.ArrayList;
import java.util.List;

import entities.ConfigEntity;
import entities.DisscountEntity;

public class Disscounts {
	private List<DisscountEntity> list;
	
	public Disscounts() {
		// TODO Auto-generated constructor stub
		list = new ArrayList<DisscountEntity>();
	}

	public List<DisscountEntity> getList() {
		return list;
	}

	public void setList(List<DisscountEntity> disscounts) {
		this.list = disscounts;
	}

	public Disscounts(List<DisscountEntity> disscounts) {
		super();
		this.list = disscounts;
	}

}
