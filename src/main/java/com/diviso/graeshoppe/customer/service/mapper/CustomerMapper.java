package com.diviso.graeshoppe.customer.service.mapper;

import com.diviso.graeshoppe.customer.domain.*;
import com.diviso.graeshoppe.customer.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring", uses = {ContactMapper.class})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

    @Mapping(source = "contact.id", target = "contactId")
    CustomerDTO toDto(Customer customer);

    @Mapping(source = "contactId", target = "contact")
    @Mapping(target = "favouritestores", ignore = true)
    @Mapping(target = "removeFavouritestore", ignore = true)
    @Mapping(target = "favouriteproducts", ignore = true)
    @Mapping(target = "removeFavouriteproduct", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
