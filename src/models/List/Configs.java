package models.List;

import java.util.ArrayList;
import java.util.List;

import entities.ConfigEntity;

public class Configs {
	private List<ConfigEntity> list;

	public Configs() {
		// TODO Auto-generated constructor stub
		list = new ArrayList<ConfigEntity>();
	}


	public List<ConfigEntity> getList() {
		return list;
	}

	public void setList(List<ConfigEntity> configs) {
		this.list = configs;
	}
	
	public Configs(List<ConfigEntity> configs) {
		super();
		this.list = configs;
	}
}
