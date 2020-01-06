package com.diviso.graeshoppe.customer.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class FavouriteStoreMapperTest {

    private FavouriteStoreMapper favouriteStoreMapper;

    @BeforeEach
    public void setUp() {
        favouriteStoreMapper = new FavouriteStoreMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(favouriteStoreMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(favouriteStoreMapper.fromId(null)).isNull();
    }
}
