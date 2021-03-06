package com.example.patagoniatest.service;

import com.example.patagoniatest.entities.Loan;
import com.example.patagoniatest.feignClient.LoanFeignClient;
import com.example.patagoniatest.model.Client;
import com.example.patagoniatest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoanFeignClient loanFeignClient;


    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public void update(Long id, Client client) throws IllegalStateException {
        if (clientRepository.existsById(id)) {
            Optional<Client> clientId = clientRepository.findById(id);
            Client client1= clientId.get();
            if (!client.getFullName().equals(client1.getFullName()) || client.getIncome() != client1.getIncome()) {
                client1 = clientRepository.save(client);
            }
        } else {
            throw new IllegalStateException("el cliente con el id solicitado, no existe");
        }
    }

    public Loan saveLoan(int clientId, Loan loan) {
        loan.setClientId(clientId);
        Loan loanNew = loanFeignClient.save(loan);
        return loanNew;
    }
}
