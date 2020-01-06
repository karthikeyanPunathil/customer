package com.diviso.graeshoppe.customer.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.customer.web.rest.TestUtil;

public class FavouriteStoreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FavouriteStore.class);
        FavouriteStore favouriteStore1 = new FavouriteStore();
        favouriteStore1.setId(1L);
        FavouriteStore favouriteStore2 = new FavouriteStore();
        favouriteStore2.setId(favouriteStore1.getId());
        assertThat(favouriteStore1).isEqualTo(favouriteStore2);
        favouriteStore2.setId(2L);
        assertThat(favouriteStore1).isNotEqualTo(favouriteStore2);
        favouriteStore1.setId(null);
        assertThat(favouriteStore1).isNotEqualTo(favouriteStore2);
    }
}
