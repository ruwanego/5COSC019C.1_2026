package uk.ac.westminster.products_api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>();

    public CustomerController() {
        customers.add(new Customer(1L, "Ada Lovelace", "ada@example.com",
                new Address("115 New Cavendish Street", "London", "W1W 6UW")));
        customers.add(new Customer(2L, "Charles Babbage", "charles@example.com",
                new Address("1 Dorset Street", "London", "W1U 4EG")));
        customers.add(new Customer(3L, "Grace Hopper", "grace@example.com",
                new Address("10 Downing Street", "London", "SW1A 2AA")));
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        for (Customer c : customers) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Customer " + id + " not found");
    }
}