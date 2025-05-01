package org.example.ebankingbackend.web;

import lombok.AllArgsConstructor;
import org.example.ebankingbackend.dtos.CustomerDTO;
import org.example.ebankingbackend.entities.Customer;
import org.example.ebankingbackend.exceptions.CustomerNotFoundException;
import org.example.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// c'est un web service
@RestController
@AllArgsConstructor
public class CustomerRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        bankAccountService.deleteCustomer(id);
    }

}


/*
@RequestBody CustomerDTO request : indiquer a spring comme quoi les données de customerDTO on va
les récupérer a partir du corps de la requétte (@RequestBody) en format Json
 */
