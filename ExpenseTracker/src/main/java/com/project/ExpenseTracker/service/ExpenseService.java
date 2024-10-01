package com.project.ExpenseTracker.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ExpenseTracker.dto.ExpenseDTO;
import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.repository.ExpenseRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepo expenseRepo;
	
	public Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO) {
		expense.setTitle(expenseDTO.getTitle());
		expense.setDate(expenseDTO.getDate());
		expense.setAmount(expenseDTO.getAmount());
		expense.setCategory(expenseDTO.getCategory());
		expense.setDescription(expenseDTO.getDescription());
		
		return expenseRepo.save(expense);
	}
	
	public List<Expense> getAllExpenses(){
		return expenseRepo.findAll().stream()
				  .sorted(Comparator.comparing(Expense::getDate).reversed())
				  .collect(Collectors.toList());
	}
	
	public Expense getExpenseById(Long id) {
		Optional<Expense> optionalExpense = expenseRepo.findById(id);
		if(optionalExpense.isPresent()) {
			return optionalExpense.get();
		}else {
			throw new EntityNotFoundException("Expense is not present with id "+id);
		}
		
	}
	
	public Expense updateExpense(Long id,ExpenseDTO expenseDTO) {
		Optional<Expense> optionalExpense = expenseRepo.findById(id);
		if(optionalExpense.isPresent()) {
			return saveOrUpdateExpense(optionalExpense.get(),expenseDTO);
		}else {
			throw new EntityNotFoundException("Expense is not present with id "+id);
		}
	}
	
	public void deleteExpense(Long id) {
		Optional<Expense> optionalExpense = expenseRepo.findById(id);
		if(optionalExpense.isPresent()) {
			expenseRepo.deleteById(id);
		}else {
			throw new EntityNotFoundException("Expense is not present with id "+id);
		}
	}
}
