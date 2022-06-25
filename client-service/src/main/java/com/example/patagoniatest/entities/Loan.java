package com.example.patagoniatest.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    private int id;
    private double amount;
    private String type;
    private int clientId;

}
