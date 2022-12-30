package me.habbazi.customerservice.mappers;

import me.habbazi.customerservice.dto.CustomerRequestDTO;
import me.habbazi.customerservice.dto.CustomerResponseDTO;
import me.habbazi.customerservice.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
    Customer CustomerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);
}
