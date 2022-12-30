package me.habbazi.customerservice.services;

import me.habbazi.customerservice.dto.CustomerRequestDTO;
import me.habbazi.customerservice.dto.CustomerResponseDTO;
import me.habbazi.customerservice.entities.Customer;
import me.habbazi.customerservice.mappers.CustomerMapper;
import me.habbazi.customerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {
        Customer customer = customerRepository.findById(id).get();
        return customerMapper.customerToCustomerResponseDTO(customer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOList = customerList.stream().map(
                c -> customerMapper.customerToCustomerResponseDTO(c)
        ).collect(Collectors.toList());
        return customerResponseDTOList;
    }

    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {
        //Customer customer = new Customer();
        //customer.setId(customerRequestDTO.getId());
        //customer.setName(customerRequestDTO.getName());
        //customer.setEmail(customerRequestDTO.getEmail());
        // ------>
        Customer customer = customerMapper.CustomerRequestDTOToCustomer(customerRequestDTO);
        Customer saveCustomer = customerRepository.save(customer);

        //CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        //customerResponseDTO.setId(saveCustomer.getId());
        //customerResponseDTO.setName(saveCustomer.getName());
        //customerResponseDTO.setEmail(saveCustomer.getEmail());
        // ------>
        CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(saveCustomer);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.CustomerRequestDTOToCustomer(customerRequestDTO);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerResponseDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomer(String id) {
        Customer customer = customerRepository.findById(id).get();
        customerRepository.delete(customer);

        /* if we get a CustomerRequestDTO Object, we can do it this way :
        * Customer customer = customerMapper.customerRequestDTOToCustomer(cusomerRequestDTO);
        * customerRepository.delete(customer);
        * */
    }
}
