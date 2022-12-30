package me.habbazi.billingservice.mappers;

import me.habbazi.billingservice.dto.InvoiceRequestDTO;
import me.habbazi.billingservice.dto.InvoiceResponseDTO;
import me.habbazi.billingservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceResponseDTO invoiceToInvoiceResponseDTO(Invoice invoice);
    Invoice InvoiceRequestDTOToInvoice(InvoiceRequestDTO invoiceRequestDTO);
}
