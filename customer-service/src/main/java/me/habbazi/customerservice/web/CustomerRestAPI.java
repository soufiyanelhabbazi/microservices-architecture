package me.habbazi.customerservice.web;

import me.habbazi.customerservice.dto.CustomerRequestDTO;
import me.habbazi.customerservice.dto.CustomerResponseDTO;
import me.habbazi.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api")
public class CustomerRestAPI {
    private CustomerService customerService;

    public CustomerRestAPI(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping(path="/customers")
    public List<CustomerResponseDTO> listCustomers() {
        return customerService.listCustomers();
    }
    @GetMapping(path="/customers/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id) {
        return customerService.getCustomer(id);
    }

    @PostMapping(path = "/customers")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customer) {
        customer.setId(UUID.randomUUID().toString());
        return customerService.saveCustomer(customer);
    }

    @PutMapping(path="/customers/{id}")
    public CustomerResponseDTO update(@PathVariable String id, CustomerRequestDTO customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping(path="/customers/{id}")
    public void delete(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }
}
