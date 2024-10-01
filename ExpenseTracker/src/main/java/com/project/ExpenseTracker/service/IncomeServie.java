package com.project.ExpenseTracker.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ExpenseTracker.dto.IncomeDTO;
import com.project.ExpenseTracker.entity.Income;
import com.project.ExpenseTracker.repository.IncomeRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IncomeServie {

	@Autowired
	private IncomeRepo incomeRepo;
	
	public Income saveOrUpdateIncome(Income income,IncomeDTO incomeDTO) {
		income.setTitle(incomeDTO.getTitle());
		income.setDate(incomeDTO.getDate());
		income.setAmount(incomeDTO.getAmount());
		income.setCategory(incomeDTO.getCategory());
		income.setDescription(incomeDTO.getDescription());
		
		return incomeRepo.save(income);
	}
	
	public List<Income> getAllIncomes(){
		return incomeRepo.findAll().stream()
				  .sorted(Comparator.comparing(Income::getDate).reversed())
				  .collect(Collectors.toList());
	}
	
	public Income getIncomeById(Long id) {
		Optional<Income> optionalIncome = incomeRepo.findById(id);
		if(optionalIncome.isPresent()) {
			return optionalIncome.get();
		}else {
			throw new EntityNotFoundException("Income is not present with id "+id);
		}
		
	}
	
	public Income updateIncome(Long id,IncomeDTO incomeDTO) {
		Optional<Income> optionalIncome = incomeRepo.findById(id);
		if(optionalIncome.isPresent()) {
			return saveOrUpdateIncome(optionalIncome.get(),incomeDTO);
		}else {
			throw new EntityNotFoundException("Expense is not present with id "+id);
		}
	}
	
	public void deleteIncome(Long id) {
		Optional<Income> optionalIncome = incomeRepo.findById(id);
		if(optionalIncome.isPresent()) {
			incomeRepo.deleteById(id);
		}else {
			throw new EntityNotFoundException("Expense is not present with id "+id);
		}
	}
}
