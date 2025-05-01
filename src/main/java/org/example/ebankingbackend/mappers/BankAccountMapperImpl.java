package org.example.ebankingbackend.mappers;


import org.example.ebankingbackend.dtos.CustomerDTO;
import org.example.ebankingbackend.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {

    /*
    il existe un framework qui faire ca, nous on crée seulement l'interface et déclarer uniquement
    la signature de la methode
    (parce que ça c'est un code technique). Le framework le plus util, il y a MapStruct
     */
    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        // permet de recopier les propriétés d'un objet vers un autre (sans besoin de fair set set ...)
        BeanUtils.copyProperties(customer, customerDTO);
        //customerDTO.setId(customer.getId());
        //customerDTO.setName(customer.getName());
        //customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO) {

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }
}
