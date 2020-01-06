package com.diviso.graeshoppe.customer.service.impl;

import com.diviso.graeshoppe.customer.service.CustomerService;
import com.diviso.graeshoppe.customer.service.UniqueCustomerIDService;
import com.diviso.graeshoppe.customer.client.SMS.SMSResourceApiIN;
import com.diviso.graeshoppe.customer.client.SMS.SMSResourceApiUK;
import com.diviso.graeshoppe.customer.domain.Customer;
import com.diviso.graeshoppe.customer.domain.OTPChallenge;
import com.diviso.graeshoppe.customer.domain.OTPResponse;
import com.diviso.graeshoppe.customer.repository.CustomerRepository;
import com.diviso.graeshoppe.customer.repository.search.CustomerSearchRepository;
import com.diviso.graeshoppe.customer.service.dto.CustomerDTO;
import com.diviso.graeshoppe.customer.service.dto.UniqueCustomerIDDTO;
import com.diviso.graeshoppe.customer.service.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Customer}.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    
	@Autowired
	private SMSResourceApiUK smsResourceApiUK;
	@Autowired
	private SMSResourceApiIN smsResourceApiIN;
	
	
	@Value("${smsgateway.credentials.in-apiKey}")
	private String apiKey_IN;

	@Value("${smsgateway.in-sender}")
	private String SMSsender_IN;

	@Value("${smsgateway.credentials.uk-apiKey}")
	private String apiKey_UK;

	@Value("${smsgateway.uk-sender}")
	private String SMSsender_UK;
    
	@Value("${app.customerId-prefix}")
	private String uniqueIdPrefix;
    
	@Autowired
	private UniqueCustomerIDService uniqueIdService;

    private final CustomerMapper customerMapper;

    private final CustomerSearchRepository customerSearchRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, CustomerSearchRepository customerSearchRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerSearchRepository = customerSearchRepository;
    }

    /**
     * Save a customer.
     *
     * @param customerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        log.debug("Request to save Customer : {}", customerDTO);
		UniqueCustomerIDDTO uniqueId=uniqueIdService.save(new UniqueCustomerIDDTO());
		customerDTO.setCustomerUniqueId(uniqueIdPrefix+""+uniqueId.getId());
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        CustomerDTO result = customerMapper.toDto(customer);
        customerSearchRepository.save(customer);
        return result;
    }

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        return customerRepository.findAll(pageable)
            .map(customerMapper::toDto);
    }


    /**
     * Get one customer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        return customerRepository.findById(id)
            .map(customerMapper::toDto);
    }

    /**
     * Delete the customer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.deleteById(id);
        customerSearchRepository.deleteById(id);
    }

    /**
     * Search for the customer corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Customers for query {}", query);
        return customerSearchRepository.search(queryStringQuery(query), pageable)
            .map(customerMapper::toDto);
    }
	@Override
	public OTPResponse sendSMS(Long numbers) {
		if (numbers.toString().substring(0, 2).equals("91")) {
			log.info("it is an indian number");
			String message = "Dear User, Enter your OTP to complete registration. OTP to verify your Mobile is ";
			return smsResourceApiIN.sendSMS(message, apiKey_IN, numbers, SMSsender_IN);
		} else {
			log.info("it is not an indian number");
			String message = "Dear User, Enter your OTP to complete registration. OTP to verify your Mobile is ";
			return smsResourceApiUK.sendSMS(message, apiKey_UK, numbers, SMSsender_UK);
		}

	}

	@Override
	public OTPChallenge verifyOTP(Long numbers, String code) {
		if (numbers.toString().substring(0, 2).equals("91")) {
			return smsResourceApiIN.verifyOTP(numbers, code, apiKey_IN);
		} else {
			return smsResourceApiUK.verifyOTP(numbers, code, apiKey_UK);

		}
	}

	@Override
	public Customer findByIdpCode(String idpCode) {
		return customerRepository.findByIdpCode(idpCode).get();
	}

	@Override
	public Optional<CustomerDTO> findByMobileNumber(Long mobileNumber) {

		return customerRepository.findByContact_MobileNumber(mobileNumber).map(customerMapper::toDto);
	}

	@Override
	public Boolean checkUserExists(String idpCode) {
		Boolean result= false;
		if(customerRepository.findByIdpCode(idpCode).isPresent()) {
			result =  true;
		}
		
		return result;
	}

	@Override
	public Long findLoyaltyPointByIdpCode(String idpCode) {
	
		return customerRepository.findLoyaltyPointByIdpCode(idpCode);
	}

}
