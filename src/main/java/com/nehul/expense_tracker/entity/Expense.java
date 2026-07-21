package com.nehul.expense_tracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Expense {
    private String title;
    @Id
    private Long id;
    private BigDecimal amount;
    private String category;
    private LocalDate date;
    private String type;
    private String notes;

}
