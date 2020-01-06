package com.diviso.graeshoppe.customer.repository.search;

import com.diviso.graeshoppe.customer.domain.FavouriteProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link FavouriteProduct} entity.
 */
public interface FavouriteProductSearchRepository extends ElasticsearchRepository<FavouriteProduct, Long> {
}
