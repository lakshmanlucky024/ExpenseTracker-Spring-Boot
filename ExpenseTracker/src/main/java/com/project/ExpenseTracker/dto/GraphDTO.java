package com.project.ExpenseTracker.dto;

import java.util.List;

import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.entity.Income;

import lombok.Data;

@Data
public class GraphDTO {

	private List<Expense> expenseList;
	
	private List<Income> incomeList;
	
}
