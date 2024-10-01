package com.project.ExpenseTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ExpenseTracker.dto.ExpenseDTO;
import com.project.ExpenseTracker.dto.IncomeDTO;
import com.project.ExpenseTracker.entity.Expense;
import com.project.ExpenseTracker.entity.Income;
import com.project.ExpenseTracker.service.IncomeServie;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/income")
@CrossOrigin("*")
public class IncomeController {

	@Autowired
	private IncomeServie incomeServie;
	
	@PostMapping
	public ResponseEntity<?> postExpense(@RequestBody IncomeDTO dto){
		Income createIncome =incomeServie.saveOrUpdateIncome(new Income(), dto);
		
		if(createIncome!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createIncome);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllIncomes(){
		return ResponseEntity.ok(incomeServie.getAllIncomes());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getIncomeById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(incomeServie.getIncomeById(id));
		} catch(EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody IncomeDTO dto ){
		try {
			return ResponseEntity.ok(incomeServie.updateIncome(id, dto));
		} catch(EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteExpense(@PathVariable Long id ){
		try {
			incomeServie.deleteIncome(id);
			return ResponseEntity.ok(null);
		} catch(EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	} 
}
