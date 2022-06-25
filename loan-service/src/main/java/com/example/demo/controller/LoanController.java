package com.example.demo.controller;

import com.example.demo.model.Loan;
import com.example.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    LoanService loanService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Loan>> getAll() {
        List<Loan> loanList = loanService.getAll();
        if(loanList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(loanList);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Loan> getById(@PathVariable("id") int id) {
        Loan loan = loanService.getById(id);
        if(loan == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(loan);
    }

    @PostMapping()
    public ResponseEntity<Loan> save(@RequestBody Loan loan) {
        Loan loan1 = loanService.save(loan);
        return ResponseEntity.ok(loan1);
    }

    @GetMapping("/getByClient/{clientId}")
    public ResponseEntity<List<Loan>> getByClientId(@PathVariable("clientId") int clientId) {
        List<Loan> loanList = loanService.getByClientId(clientId);
        if(loanList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(loanList);
    }

}
