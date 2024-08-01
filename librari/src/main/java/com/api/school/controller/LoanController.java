package com.api.school.controller;

import com.api.school.entity.Loan;
import com.api.school.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.createLoan(loan);
    }

    @PostMapping("/return/{id}")
    @PreAuthorize("hasRole('USER')")
    public void returnLoan(@PathVariable String id) {
        loanService.returnLoan(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(@PathVariable String id) {
        loanService.deleteLoan(id);
    }
}
