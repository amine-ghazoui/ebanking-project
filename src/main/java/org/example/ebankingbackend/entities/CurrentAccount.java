package org.example.ebankingbackend.entities;


import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class CurrentAccount extends BankAccount{

    private double overDraft;
}
