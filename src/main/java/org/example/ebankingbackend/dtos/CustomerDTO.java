package org.example.ebankingbackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.example.ebankingbackend.entities.BankAccount;

import java.util.List;

@Data
@Getter @Setter
public class CustomerDTO {

    private Long id;
    private String name;
    private String email;

}
