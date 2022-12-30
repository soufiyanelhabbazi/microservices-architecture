package me.habbazi.billingservice.openfeign;

import me.habbazi.billingservice.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/* On ne peut passer directement au service si on sait son adresse IP
 sans avoir recours à Eureka :
----> @FeignClient(path = "192.168.1.3/api/customers")
*/
@FeignClient(name = "CUSTOMER-SERVICE")
/* Mais c'est mieux d'utiliser Eureka puisqu'il utilise le load balancer
   même si le service est démarré en plusieurs instances*/
public interface CustomerRestClient {
    @GetMapping(path = "/api/customers/{id}")
    Customer getCustomer(@PathVariable String id);
    @GetMapping(path = "/api/customers")
    List<Customer> allCustomers();
}
