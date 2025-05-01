package org.example.ebankingbackend.dtos;

import lombok.*;


@Data
@Getter @Setter
public class CustomerDTO {

    private Long id;
    private String name;
    private String email;

}
