package com.microservicio.app.familia.entity;

import org.springframework.data.annotation.Id;

public class DataBaseSequence {
	@Id
	private String id;

	private long seq;

	public DataBaseSequence() {
		
	}
	
	public String getId() {
	        return id;
	        }
	public void setId(String id) {
	        this.id = id;
	        }
	public long getSeq() {
	        return seq;
	        }
	public void setSeq(long seq) {
	        this.seq = seq;
	        }
}
