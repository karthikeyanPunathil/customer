package com.diviso.graeshoppe.customer.repository.search;

import com.diviso.graeshoppe.customer.domain.UniqueCustomerID;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link UniqueCustomerID} entity.
 */
public interface UniqueCustomerIDSearchRepository extends ElasticsearchRepository<UniqueCustomerID, Long> {
}
