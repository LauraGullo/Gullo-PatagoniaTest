package com.example.patagoniatest.feignClient;


import com.example.patagoniatest.entities.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "loan-service")
@RequestMapping("/loan")
public interface LoanFeignClient {

    @PostMapping()
    Loan save(@RequestBody Loan loan);

}
