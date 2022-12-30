package me.habbazi.customerservice.services;

import me.habbazi.customerservice.dto.CustomerRequestDTO;
import me.habbazi.customerservice.dto.CustomerResponseDTO;
import java.util.List;

public interface CustomerService {
    CustomerResponseDTO getCustomer(String id);
    List<CustomerResponseDTO> listCustomers();
    CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO updateCustomer(CustomerRequestDTO customerRequestDTO);
    void deleteCustomer(String id);
}
