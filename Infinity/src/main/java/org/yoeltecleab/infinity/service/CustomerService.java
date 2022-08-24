package org.yoeltecleab.infinity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.yoeltecleab.infinity.model.Customer;
import org.yoeltecleab.infinity.repository.CustomerRepository;
import org.yoeltecleab.infinity.repository.UserRepository;
import org.yoeltecleab.infinity.transfer.CustomerDto;

/**
 * <pre>
 *     This class "CustomerService" Autowired the CustomerRepository class
 *     contains the method "save" to save the information to the database
 * </pre>
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public void save(CustomerDto customerDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Customer customer = new Customer();
            customer.setCustomerId(userRepository.findByEmail(auth.getName()).getUserId());
            customer.setEmail(customerDto.getEmail());
            customer.setCity(customerDto.getCity());
            customer.setCvv(customerDto.getCvv());
            customer.setAddress(customerDto.getAddress());
            customer.setCardNumber(customerDto.getCardNumber());
            customer.setExpMonth(customerDto.getExpMonth());
            customer.setExpYear(customerDto.getExpYear());
            customer.setFullName(customerDto.getFullName());
            customer.setNameCard(customerDto.getNameCard());
            customer.setZipCode(customerDto.getZipCode());
            customer.setState(customerDto.getState());
            customerRepository.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving customer information !!! ");
        }
    }
}
