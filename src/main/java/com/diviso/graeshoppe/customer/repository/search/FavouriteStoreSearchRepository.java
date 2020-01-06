package com.diviso.graeshoppe.customer.repository.search;

import com.diviso.graeshoppe.customer.domain.FavouriteStore;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link FavouriteStore} entity.
 */
public interface FavouriteStoreSearchRepository extends ElasticsearchRepository<FavouriteStore, Long> {
}
