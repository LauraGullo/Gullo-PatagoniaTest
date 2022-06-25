package com.example.demo.service;


import com.example.demo.model.Loan;
import com.example.demo.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }

    public Loan getById(int id) {
        return loanRepository.findById(id).orElse(null);
    }

    public Loan save(Loan loan) {
        Loan loan1 = loanRepository.save(loan);
        return loan1;
    }

    public List<Loan> getByClientId(int clientId) {
        return loanRepository.findByClientId(clientId);
    }
}

