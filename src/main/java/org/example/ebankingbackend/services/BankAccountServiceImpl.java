package org.example.ebankingbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ebankingbackend.entities.*;
import org.example.ebankingbackend.enums.OperationType;
import org.example.ebankingbackend.exceptions.BalanceNotSufficientException;
import org.example.ebankingbackend.exceptions.BankAccountNotFoundException;
import org.example.ebankingbackend.exceptions.CustomerNotFoundException;
import org.example.ebankingbackend.repositories.AccountOperationRepository;
import org.example.ebankingbackend.repositories.BankAccountRepository;
import org.example.ebankingbackend.repositories.CustomerRepository;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
// ajouter un attribut log pour loger les messages
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private final BeanNameAware beanNameAware;
    CustomerRepository customerRepository;
    BankAccountRepository bankAccountRepository;
    AccountOperationRepository accountOperationRepository;

    //***************************************************************************************

    // Enregistre un nouveau client dans la base de données.
    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Savin new Customer");
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    //***************************************************************************************

    //  Crée et enregistre un compte courant (Compte pour les dépenses quotidiennes) pour un client donné.
    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {

        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        CurrentAccount savedAccount =  bankAccountRepository.save(currentAccount);
        return savedAccount;
    }

    //***************************************************************************************

    // Crée et enregistre un compte épargne (Compte pour économiser de l’argent) pour un client donné.
    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        SavingAccount savedAccount =  bankAccountRepository.save(savingAccount);
        return savedAccount;
    }

    //***************************************************************************************

    // Récupère la liste de tous les clients enregistrés.
    @Override
    public List<Customer> listCustomers() {

        return customerRepository.findAll();
    }

    // Recherche un compte bancaire par son identifiant.
    @Override
    public BankAccount getBankAccount(String accountId)throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).
                orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));

        return bankAccount;
    }

    //***************************************************************************************

    //  Effectue une opération de débit (Sortie d’argent du compte) sur un compte bancaire donné.
    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount = getBankAccount(accountId);
        if (bankAccount.getBalance() < amount) {
            throw new BalanceNotSufficientException("Balance not Sufficient");
        }
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
    }

    //***************************************************************************************

    // Effectue une opération de crédit (Entrée d’argent dans le compte) sur un compte bancaire donné.
    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = getBankAccount(accountId);

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }

    //***************************************************************************************

    // Effectue une opération de crédit sur un compte bancaire donné.
    @Override
    public void transfert(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource, amount, "Transfer to "+accountIdDestination);
        credit(accountIdDestination, amount, "Transfer from "+accountIdSource);
    }

    //***************************************************************************************

    @Override
    public List<BankAccount> bankAccountList(){
        return bankAccountRepository.findAll();
    }

}
