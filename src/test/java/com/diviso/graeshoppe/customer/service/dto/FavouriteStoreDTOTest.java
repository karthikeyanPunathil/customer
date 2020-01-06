package com.diviso.graeshoppe.customer.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.customer.web.rest.TestUtil;

public class FavouriteStoreDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FavouriteStoreDTO.class);
        FavouriteStoreDTO favouriteStoreDTO1 = new FavouriteStoreDTO();
        favouriteStoreDTO1.setId(1L);
        FavouriteStoreDTO favouriteStoreDTO2 = new FavouriteStoreDTO();
        assertThat(favouriteStoreDTO1).isNotEqualTo(favouriteStoreDTO2);
        favouriteStoreDTO2.setId(favouriteStoreDTO1.getId());
        assertThat(favouriteStoreDTO1).isEqualTo(favouriteStoreDTO2);
        favouriteStoreDTO2.setId(2L);
        assertThat(favouriteStoreDTO1).isNotEqualTo(favouriteStoreDTO2);
        favouriteStoreDTO1.setId(null);
        assertThat(favouriteStoreDTO1).isNotEqualTo(favouriteStoreDTO2);
    }
}
