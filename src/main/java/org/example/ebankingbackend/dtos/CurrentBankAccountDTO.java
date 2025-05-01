package org.example.ebankingbackend.dtos;



import lombok.*;
import org.example.ebankingbackend.entities.BankAccount;
import org.example.ebankingbackend.enums.AccountStatus;

import java.util.Date;

@Data
@Getter @Setter
public class CurrentBankAccountDTO extends BankAccount {

    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;
}
