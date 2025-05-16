package org.example.ebankingbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.ebankingbackend.enums.OperationType;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class AccountOperation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    private String description;
    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne
    private BankAccount bankAccount;

}
