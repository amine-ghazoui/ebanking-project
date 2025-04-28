package org.example.ebankingbackend.entities;


import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class SavingAccount extends BankAccount{

    private double interestRate;
}
