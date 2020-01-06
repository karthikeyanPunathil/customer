package com.diviso.graeshoppe.customer.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diviso.graeshoppe.customer.domain.Customer;
import com.diviso.graeshoppe.customer.domain.OTPChallenge;
import com.diviso.graeshoppe.customer.domain.OTPResponse;
import com.diviso.graeshoppe.customer.repository.CustomerRepository;
import com.diviso.graeshoppe.customer.service.CustomerService;
import com.diviso.graeshoppe.customer.service.dto.CustomerDTO;
import com.diviso.graeshoppe.customer.service.mapper.CustomerMapper;
import com.diviso.graeshoppe.customer.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.diviso.graeshoppe.customer.domain.Customer}.
 */
@RestController
@RequestMapping("/api")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private static final String ENTITY_NAME = "customerCustomer";
    
	@Autowired
	private CustomerMapper customerMapper;


    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerService customerService;
    
    private  CustomerRepository customerRepository;
    
    

    public CustomerResource(CustomerService customerService,CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

	@GetMapping("/findByMobileNumber/{mobileNumber}")
	public ResponseEntity<CustomerDTO> findByMobileNumber(@PathVariable Long mobileNumber) {
		Optional<CustomerDTO> customerDTO=customerService.findByMobileNumber(mobileNumber);
		return ResponseUtil.wrapOrNotFound(customerDTO);
	}
	
	/**
	 * POST /customers : Create a new customer.
	 *
	 * @param customerDTO the customerDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         customerDTO, or with status 400 (Bad Request) if the customer has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/customers")
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {

		log.debug("REST request to save Customer : {}", customerDTO);

		if (customerDTO.getId() != null) {
			throw new BadRequestAlertException("A new customer cannot already have an ID", ENTITY_NAME, "idexists");
		}

		CustomerDTO result1 = customerService.save(customerDTO);
		if (result1.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}

		CustomerDTO result = customerService.save(result1);

		return ResponseEntity.created(new URI("/api/customers/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(applicationName, true,ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /customers : Updates an existing customer.
	 *
	 * @param customerDTO the customerDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         customerDTO, or with status 400 (Bad Request) if the customerDTO is
	 *         not valid, or with status 500 (Internal Server Error) if the
	 *         customerDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/customers")
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
		log.debug("REST request to update Customer : {}", customerDTO);
		if (customerDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		CustomerDTO result = customerService.save(customerDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(applicationName, true,ENTITY_NAME, customerDTO.getId().toString())).body(result);
	}

	/**
	 * GET /customers : get all the customers.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of customers in
	 *         body
	 */
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDTO>> getAllCustomers(Pageable pageable) {
		log.debug("REST request to get a page of Customers");
		Page<CustomerDTO> page = customerService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /customers/:id : get the "id" customer.
	 *
	 * @param id the id of the customerDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         customerDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
		log.debug("REST request to get Customer : {}", id);
		Optional<CustomerDTO> customerDTO = customerService.findOne(id);
		return ResponseUtil.wrapOrNotFound(customerDTO);
	}

	/**
	 * DELETE /customers/:id : delete the "id" customer.
	 *
	 * @param id the id of the customerDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		log.debug("REST request to delete Customer : {}", id);
		customerService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
	}

	/**
	 * SEARCH /_search/customers?query=:query : search for the customer
	 * corresponding to the query.
	 *
	 * @param query    the query of the customer search
	 * @param pageable the pagination information
	 * @return the result of the search
	 */
	@GetMapping("/_search/customers")
	public ResponseEntity<List<CustomerDTO>> searchCustomers(@RequestParam String query, Pageable pageable) {
		log.debug("REST request to search for a page of Customers for query {}", query);
		Page<CustomerDTO> page = customerService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	@PostMapping("/customer/modelToDto")
	public ResponseEntity<CustomerDTO> modelToDto(@RequestBody Customer customer) {
		log.debug("REST request to convert to DTO");
		return ResponseEntity.ok().body(customerMapper.toDto(customer));
	}
	
	@PostMapping("/customer/sendSMS")
	public ResponseEntity<CustomerDTO> sendSMS(@RequestBody Customer customer) {
		log.debug("REST request to convert to DTO");
		return ResponseEntity.ok().body(customerMapper.toDto(customer));
	}
	
	@PostMapping("/customer/otp_send")
	OTPResponse sendSMS( @RequestParam long numbers) {
    			
		return customerService.sendSMS( numbers);
	}
	
    @PostMapping("/customer/otp_challenge")
	OTPChallenge verifyOTP(@RequestParam long numbers, @RequestParam String code) {
  			
		return customerService.verifyOTP(numbers,code);
	}

	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	@GetMapping("/findByReference/{reference}")
	public Customer findByReference(@PathVariable String reference) {
		return customerService.findByIdpCode(reference);
	}
	
	
	@GetMapping("/checkUserExists/{reference}")
	public Boolean checkUserExists(@PathVariable String reference) {
		return customerService.checkUserExists(reference);
	}
	
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	
	@PostMapping("/updateLoyaltyPoint/{idpCode}/{point}")
	public CustomerDTO updateLoyaltyPoint(@PathVariable String idpCode, @PathVariable Long point) {
		
		
		Customer customer=customerService.findByIdpCode(idpCode);
		
		if(customer.getLoyaltyPoint()==null) {
			
			customer.setLoyaltyPoint(0L);
			
			System.out.println("set loyalty first +++++++++++++++++++++++++++++"+customer.getLoyaltyPoint());
			
		}
	
		if(customer.getLoyaltyPoint()==10)
		{
			customer.setLoyaltyPoint(0L);
		}
		
		customer.setLoyaltyPoint(customer.getLoyaltyPoint()+point);
				
		return customerService.save(customerMapper.toDto(customer));
		
	}

	@GetMapping("/findLoyaltyPointByIdpCode/{idpCode}")
	public Long findLoyaltyPointByIdpCode(@PathVariable String idpCode) {
		
		    if(customerService.findLoyaltyPointByIdpCode(idpCode)==null)
		    {
		    	return 0L;
		    }
		
		    return customerService.findLoyaltyPointByIdpCode(idpCode);
		
	}
	
}
