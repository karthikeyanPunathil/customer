package com.diviso.graeshoppe.customer.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.customer.web.rest.TestUtil;

public class FavouriteProductDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FavouriteProductDTO.class);
        FavouriteProductDTO favouriteProductDTO1 = new FavouriteProductDTO();
        favouriteProductDTO1.setId(1L);
        FavouriteProductDTO favouriteProductDTO2 = new FavouriteProductDTO();
        assertThat(favouriteProductDTO1).isNotEqualTo(favouriteProductDTO2);
        favouriteProductDTO2.setId(favouriteProductDTO1.getId());
        assertThat(favouriteProductDTO1).isEqualTo(favouriteProductDTO2);
        favouriteProductDTO2.setId(2L);
        assertThat(favouriteProductDTO1).isNotEqualTo(favouriteProductDTO2);
        favouriteProductDTO1.setId(null);
        assertThat(favouriteProductDTO1).isNotEqualTo(favouriteProductDTO2);
    }
}
