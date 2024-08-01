package com.api.school.service;


import com.api.school.entity.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanRepository extends MongoRepository<Loan, String> {
}
