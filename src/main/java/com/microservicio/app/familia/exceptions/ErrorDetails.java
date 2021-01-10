package com.microservicio.app.familia.exceptions;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LogBD")
public class ErrorDetails implements Serializable{

	private static final long serialVersionUID = 5688722229547912403L;
	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";
	
	private Date timeStamp;
	private String message;
	private String details;
	
	 
	public ErrorDetails() {
		
	}

	public ErrorDetails(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	

}
