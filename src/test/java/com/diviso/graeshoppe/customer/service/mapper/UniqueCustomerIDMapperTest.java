package com.diviso.graeshoppe.customer.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class UniqueCustomerIDMapperTest {

    private UniqueCustomerIDMapper uniqueCustomerIDMapper;

    @BeforeEach
    public void setUp() {
        uniqueCustomerIDMapper = new UniqueCustomerIDMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(uniqueCustomerIDMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(uniqueCustomerIDMapper.fromId(null)).isNull();
    }
}
