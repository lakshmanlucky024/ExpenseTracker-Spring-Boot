package com.project.ExpenseTracker.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class IncomeDTO {

	private long id;
	
	private String title;
	
	private String description;
	
	private String category;
	
	private LocalDate date;
	
	private Integer amount;
}
