package com.project.ExpenseTracker.dto;

import java.util.Optional;

import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.entity.Income;

import lombok.Data;

@Data
public class StatsDTO {

	private Double income;
	
	private Double expense;
	
	private Optional<Income> latestIncome;
	
	private Optional<Expense> latestExense;
	
	private Double balance;
	
	private Double minIncome;
	
	private Double maxIncome;

	private Double minExpense;

	private Double maxExpense;

	
	
}
