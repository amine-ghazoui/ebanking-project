package org.example.ebankingbackend.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
// regarder CurrentAccount pour comprendre
//@DiscriminatorValue("SA")
@Data
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class SavingAccount extends BankAccount{

    private double interestRate;
}
