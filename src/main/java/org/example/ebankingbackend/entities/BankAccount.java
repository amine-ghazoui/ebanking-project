package org.example.ebankingbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.ebankingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class BankAccount {

    @Id
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;

    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.EAGER)
    private List<AccountOperation>accountOperations;

    @ManyToOne
    private Customer customer;
}
