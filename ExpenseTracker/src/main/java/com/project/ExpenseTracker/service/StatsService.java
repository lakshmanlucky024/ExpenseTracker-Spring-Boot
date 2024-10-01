package com.project.ExpenseTracker.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ExpenseTracker.dto.GraphDTO;
import com.project.ExpenseTracker.dto.StatsDTO;
import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.entity.Income;
import com.project.ExpenseTracker.dto.GraphDTO;
import com.project.ExpenseTracker.repository.ExpenseRepo;
import com.project.ExpenseTracker.repository.IncomeRepo;

@Service
public class StatsService {

	@Autowired
	private IncomeRepo incomeRepo;
	
	@Autowired
	private ExpenseRepo expenseRepo;
	
	public GraphDTO getChartData() {
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusDays(27);
		
		GraphDTO graphDTO = new GraphDTO();
		graphDTO.setExpenseList(expenseRepo.findByDateBetween(startDate, endDate));
		graphDTO.setIncomeList(incomeRepo.findByDateBetween(startDate, endDate));
		
		return graphDTO;
	}
	
	public StatsDTO getStats() {
		
		Double totalIncome = incomeRepo.SumAllAmounts();
		Double totalExpense = expenseRepo.SumAllAmounts();
		
		Optional<Income> optionalIncome = incomeRepo.findFirstByOrderByDateDesc();
		Optional<Expense> optionalExpense = expenseRepo.findFirstByOrderByDateDesc();
		
		
		StatsDTO statsDTO = new StatsDTO();
		statsDTO.setIncome(totalIncome);
		statsDTO.setExpense(totalExpense);
		
		if(optionalIncome.isPresent()) {
			statsDTO.setLatestIncome(optionalIncome);
		}
		
		if(optionalExpense.isPresent()) {
			statsDTO.setLatestExense(optionalExpense);
		}
		
		statsDTO.setBalance(totalIncome-totalExpense);
		
		List<Income> incomeList = incomeRepo.findAll();
		List<Expense> expenseList = expenseRepo.findAll();
		
		OptionalDouble minIncome =incomeList.stream().mapToDouble(Income::getAmount).min();
		OptionalDouble maxIncome =incomeList.stream().mapToDouble(Income::getAmount).max();
		
		OptionalDouble minExpense =expenseList.stream().mapToDouble(Expense::getAmount).min();
		OptionalDouble maxExpense =expenseList.stream().mapToDouble(Expense::getAmount).max();
		
		statsDTO.setMaxExpense(maxExpense.isPresent()? maxExpense.getAsDouble() : null);
		statsDTO.setMinExpense(minExpense.isPresent()? minExpense.getAsDouble() : null);
		
		statsDTO.setMaxIncome(maxIncome.isPresent()? maxIncome.getAsDouble() : null);
		statsDTO.setMaxExpense(minIncome.isPresent()? minIncome.getAsDouble() : null);
		
		
		return statsDTO;
	}
}
