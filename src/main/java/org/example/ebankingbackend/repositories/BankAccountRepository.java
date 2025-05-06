package org.example.ebankingbackend.repositories;

import org.example.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    String id(String id);
    void deleteByCustomerId(Long customer_id);
    BankAccount findByCustomerId(Long customer_id);
}
