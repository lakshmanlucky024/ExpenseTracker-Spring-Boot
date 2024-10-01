package com.project.ExpenseTracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.entity.Income;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long>{

	public List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

	@Query("SELECT SUM(amount) FROM Expense")
	public Double SumAllAmounts();
	
	public Optional<Expense> findFirstByOrderByDateDesc();
}
