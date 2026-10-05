package stage.ifm.customerservice.service;

import org.springframework.stereotype.Service;
import stage.ifm.customerservice.entities.Customer;
import stage.ifm.customerservice.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer not found!"));
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
