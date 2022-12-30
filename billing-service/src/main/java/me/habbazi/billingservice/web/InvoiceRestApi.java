package me.habbazi.billingservice.web;

import me.habbazi.billingservice.dto.InvoiceRequestDTO;
import me.habbazi.billingservice.dto.InvoiceResponseDTO;
import me.habbazi.billingservice.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InvoiceRestApi {
    private InvoiceService invoiceService;

    public InvoiceRestApi(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable(name="id") String invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }
    @GetMapping(path = "/invoices")
    public List<InvoiceResponseDTO> listInvoices() {
        return invoiceService.listInvoices();
    }

    @GetMapping(path = "/invoicesByCustomer/{customerId}")
    public List<InvoiceResponseDTO> getInvoicesByCustomer(@PathVariable String customerId) {
        return invoiceService.customerInvoices(customerId);
    }
    @PostMapping(path = "/invoices")
    public InvoiceResponseDTO saveInvoice(@RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        return invoiceService.saveInvoice(invoiceRequestDTO);
    }
    @PatchMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO updateInvoice(@PathVariable String id, @RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        return invoiceService.updateInvoice(invoiceRequestDTO);
    }
    @DeleteMapping(path="/invoices/{id}")
    public void deleteInvoice(@PathVariable String id) {
        invoiceService.deleteInvoice(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}