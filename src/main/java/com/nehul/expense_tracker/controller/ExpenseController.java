package com.nehul.expense_tracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @PostMapping
    public String addExpense() {
        return "The controller api is working!";
    }
    @GetMapping
    public String viewExpenses() {
        return "The controller api is working!";
    } 
    
}

