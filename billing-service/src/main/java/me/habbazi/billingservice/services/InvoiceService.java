package me.habbazi.billingservice.services;

import me.habbazi.billingservice.dto.InvoiceRequestDTO;
import me.habbazi.billingservice.dto.InvoiceResponseDTO;
import me.habbazi.billingservice.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO getInvoice(String invoiceId);
    List<InvoiceResponseDTO> listInvoices();
    List<InvoiceResponseDTO> customerInvoices(String customerID);
    InvoiceResponseDTO saveInvoice(InvoiceRequestDTO invoice);
    InvoiceResponseDTO updateInvoice(InvoiceRequestDTO invoice);
    void deleteInvoice(String id);
}
