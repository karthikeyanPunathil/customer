package com.diviso.graeshoppe.customer.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.customer.web.rest.TestUtil;

public class UniqueCustomerIDTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UniqueCustomerID.class);
        UniqueCustomerID uniqueCustomerID1 = new UniqueCustomerID();
        uniqueCustomerID1.setId(1L);
        UniqueCustomerID uniqueCustomerID2 = new UniqueCustomerID();
        uniqueCustomerID2.setId(uniqueCustomerID1.getId());
        assertThat(uniqueCustomerID1).isEqualTo(uniqueCustomerID2);
        uniqueCustomerID2.setId(2L);
        assertThat(uniqueCustomerID1).isNotEqualTo(uniqueCustomerID2);
        uniqueCustomerID1.setId(null);
        assertThat(uniqueCustomerID1).isNotEqualTo(uniqueCustomerID2);
    }
}
