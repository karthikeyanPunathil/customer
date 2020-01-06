package com.diviso.graeshoppe.customer.service.mapper;

import com.diviso.graeshoppe.customer.domain.*;
import com.diviso.graeshoppe.customer.service.dto.UniqueCustomerIDDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UniqueCustomerID} and its DTO {@link UniqueCustomerIDDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UniqueCustomerIDMapper extends EntityMapper<UniqueCustomerIDDTO, UniqueCustomerID> {



    default UniqueCustomerID fromId(Long id) {
        if (id == null) {
            return null;
        }
        UniqueCustomerID uniqueCustomerID = new UniqueCustomerID();
        uniqueCustomerID.setId(id);
        return uniqueCustomerID;
    }
}
