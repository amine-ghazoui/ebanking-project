package org.example.ebankingbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.ebankingbackend.enums.OperationType;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class AccountOperation {

    @Id
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    @ManyToOne
    private BankAccount bankAccount;
}
