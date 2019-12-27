package indi.xk.report.pojo;

import java.io.Serializable;
import java.util.UUID;

public class BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public BaseEntity(){
		this.id=UUID.randomUUID().toString();
	}
}
