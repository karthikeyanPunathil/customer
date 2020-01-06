package com.diviso.graeshoppe.customer.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link FavouriteStoreSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class FavouriteStoreSearchRepositoryMockConfiguration {

    @MockBean
    private FavouriteStoreSearchRepository mockFavouriteStoreSearchRepository;

}
