package com.project.ExpenseTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ExpenseTracker.dto.GraphDTO;
import com.project.ExpenseTracker.service.StatsService;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin("*")
public class StatsController {

	@Autowired
	private StatsService statsService;
	
	@GetMapping("/chart")
	public ResponseEntity<GraphDTO> getChartData(){
		return ResponseEntity.ok(statsService.getChartData());
	}
	
	@GetMapping
	public ResponseEntity<?> getStats(){
		return ResponseEntity.ok(statsService.getStats());
	}
}
