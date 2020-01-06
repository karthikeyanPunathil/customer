package com.diviso.graeshoppe.customer.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class FavouriteProductMapperTest {

    private FavouriteProductMapper favouriteProductMapper;

    @BeforeEach
    public void setUp() {
        favouriteProductMapper = new FavouriteProductMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(favouriteProductMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(favouriteProductMapper.fromId(null)).isNull();
    }
}
