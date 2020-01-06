package com.diviso.graeshoppe.customer.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.customer.web.rest.TestUtil;

public class UniqueCustomerIDDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UniqueCustomerIDDTO.class);
        UniqueCustomerIDDTO uniqueCustomerIDDTO1 = new UniqueCustomerIDDTO();
        uniqueCustomerIDDTO1.setId(1L);
        UniqueCustomerIDDTO uniqueCustomerIDDTO2 = new UniqueCustomerIDDTO();
        assertThat(uniqueCustomerIDDTO1).isNotEqualTo(uniqueCustomerIDDTO2);
        uniqueCustomerIDDTO2.setId(uniqueCustomerIDDTO1.getId());
        assertThat(uniqueCustomerIDDTO1).isEqualTo(uniqueCustomerIDDTO2);
        uniqueCustomerIDDTO2.setId(2L);
        assertThat(uniqueCustomerIDDTO1).isNotEqualTo(uniqueCustomerIDDTO2);
        uniqueCustomerIDDTO1.setId(null);
        assertThat(uniqueCustomerIDDTO1).isNotEqualTo(uniqueCustomerIDDTO2);
    }
}
