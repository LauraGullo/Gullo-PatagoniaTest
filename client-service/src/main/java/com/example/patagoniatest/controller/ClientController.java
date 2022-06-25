package com.example.patagoniatest.controller;

import com.example.patagoniatest.entities.Loan;
import com.example.patagoniatest.model.Client;
import com.example.patagoniatest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    ClientService clientService;


    @GetMapping("/getAll")
    public List<Client> getClients() {

        return clientService.getClients();
    }

    @PostMapping("/addClient")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/{id}")
    public Optional<Client> getByid(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Client client) throws IllegalStateException {
        try {
            clientService.update(id, client);
        } catch (IllegalStateException e) {
             System.out.println(e.getMessage());
        }
    }

    @PostMapping("/saveLoan/{clientId}")
    public ResponseEntity<Loan> saveLoan(@PathVariable("clientId") int clientId, @RequestBody Loan loan) {
        if(clientService.findById((long) clientId) == null)
            return ResponseEntity.notFound().build();
        Loan loanNew = clientService.saveLoan(clientId, loan);
        return ResponseEntity.ok(loan);
    }
}
