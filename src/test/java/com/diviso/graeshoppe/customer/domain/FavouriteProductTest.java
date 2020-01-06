package com.diviso.graeshoppe.customer.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.customer.web.rest.TestUtil;

public class FavouriteProductTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FavouriteProduct.class);
        FavouriteProduct favouriteProduct1 = new FavouriteProduct();
        favouriteProduct1.setId(1L);
        FavouriteProduct favouriteProduct2 = new FavouriteProduct();
        favouriteProduct2.setId(favouriteProduct1.getId());
        assertThat(favouriteProduct1).isEqualTo(favouriteProduct2);
        favouriteProduct2.setId(2L);
        assertThat(favouriteProduct1).isNotEqualTo(favouriteProduct2);
        favouriteProduct1.setId(null);
        assertThat(favouriteProduct1).isNotEqualTo(favouriteProduct2);
    }
}
