package org.example.ebankingbackend.services;


import jakarta.transaction.Transactional;
import org.example.ebankingbackend.dtos.BankAccountDTO;
import org.example.ebankingbackend.dtos.CurrentBankAccountDTO;
import org.example.ebankingbackend.dtos.CustomerDTO;
import org.example.ebankingbackend.dtos.SavingBankAccountDTO;
import org.example.ebankingbackend.entities.BankAccount;
import org.example.ebankingbackend.entities.CurrentAccount;
import org.example.ebankingbackend.entities.Customer;
import org.example.ebankingbackend.entities.SavingAccount;
import org.example.ebankingbackend.exceptions.BalanceNotSufficientException;
import org.example.ebankingbackend.exceptions.BankAccountNotFoundException;
import org.example.ebankingbackend.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BankAccountService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId)throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId)throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId)throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfert(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();
    public CustomerDTO getCustomer(Long accountId) throws CustomerNotFoundException;

    // Enregistre un nouveau client dans la base de données.
    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);
}
