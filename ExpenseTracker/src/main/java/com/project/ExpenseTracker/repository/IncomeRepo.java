package com.project.ExpenseTracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.entity.Income;

public interface IncomeRepo extends JpaRepository<Income, Long> {

	public List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);
	
	@Query("SELECT SUM(amount) FROM Income")
	public Double SumAllAmounts();
	
	public Optional<Income> findFirstByOrderByDateDesc();
	
	
}
