package me.habbazi.billingservice.services;

import me.habbazi.billingservice.dto.InvoiceRequestDTO;
import me.habbazi.billingservice.dto.InvoiceResponseDTO;
import me.habbazi.billingservice.entities.Customer;
import me.habbazi.billingservice.entities.Invoice;
import me.habbazi.billingservice.mappers.InvoiceMapper;
import me.habbazi.billingservice.openfeign.CustomerRestClient;
import me.habbazi.billingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO getInvoice(String id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomer(invoice.getCustomerID());
        invoice .setCustomer(customer);
        return invoiceMapper.invoiceToInvoiceResponseDTO(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> listInvoices() {
        List<Invoice> listInvoices = invoiceRepository.findAll();
        for (Invoice i: listInvoices) {
            Customer c = customerRestClient.getCustomer(i.getCustomerID());
            i.setCustomer(c);
        }
        List<InvoiceResponseDTO> listInvoiceResponseDTO = listInvoices.stream().map(
                i -> invoiceMapper.invoiceToInvoiceResponseDTO(i)
        ).collect(Collectors.toList());
        return listInvoiceResponseDTO;
    }

    @Override
    public List<InvoiceResponseDTO> customerInvoices(String customerID) {
        List<Invoice> customerInvoices = invoiceRepository.findByCustomerID(customerID);
        for (Invoice i: customerInvoices) {
            Customer c = customerRestClient.getCustomer(i.getCustomerID());
            i.setCustomer(c);
        }
        List<InvoiceResponseDTO> listInvoiceResponseDTO = customerInvoices.stream()
                .map(i -> invoiceMapper.invoiceToInvoiceResponseDTO(i))
                .collect(Collectors.toList());
        return listInvoiceResponseDTO;
    }

    @Override
    public InvoiceResponseDTO saveInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        // Vérif intégrité référentielle | Invoice->Customer !!
        Customer c = null;
        try {
            c = customerRestClient.getCustomer(invoiceRequestDTO.getCustomerID());
        } catch(Exception e) {
            // throw new CustomerNotFoundException("Customer not found !");
            throw e;
        }
        Invoice invoice = invoiceMapper.InvoiceRequestDTOToInvoice(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());

        Invoice savedInvoice = invoiceRepository.save(invoice);
        savedInvoice.setCustomer(c);
        return invoiceMapper.invoiceToInvoiceResponseDTO(savedInvoice);
    }

    @Override
    public InvoiceResponseDTO updateInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        Invoice invoice = invoiceMapper.InvoiceRequestDTOToInvoice(invoiceRequestDTO);
        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.invoiceToInvoiceResponseDTO(updatedInvoice);
    }

    @Override
    public void deleteInvoice(String id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        invoiceRepository.delete(invoice);
    }
}
