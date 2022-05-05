package com.banco.produtos.crm.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 

public class FormatLocalDate {
	
	private LocalDateTime localDate;
	private String formatDateTime;
	
	public FormatLocalDate(LocalDateTime data) {
		localDate = data;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		this.formatDateTime = localDate.format(dtf);
	}
	
	public String formatDateTime() {
		return formatDateTime;
	}

}
