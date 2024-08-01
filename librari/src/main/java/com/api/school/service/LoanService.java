package com.api.school.service;

import com.api.school.entity.Loan;
import com.api.school.service.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(String id) {
        return loanRepository.findById(id).orElse(null);
    }

    public Loan createLoan(Loan loan) {
        loan.setLoanDate(LocalDate.now());
        loan.setReturned(false);
        return loanRepository.save(loan);
    }

    public void returnLoan(String id) {
        Loan loan = loanRepository.findById(id).orElse(null);
        if (loan != null) {
            loan.setReturned(true);
            loan.setReturnDate(LocalDate.now());
            loanRepository.save(loan);
        }
    }

    public void deleteLoan(String id) {
        loanRepository.deleteById(id);
    }
}
