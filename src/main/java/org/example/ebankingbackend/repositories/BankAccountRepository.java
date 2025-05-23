package org.example.ebankingbackend.repositories;

import org.example.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    String id(String id);
    void deleteByCustomerId(Long customer_id);
    List<BankAccount> findByCustomerId(Long customer_id);
}
