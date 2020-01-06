package com.diviso.graeshoppe.customer.repository.search;

import com.diviso.graeshoppe.customer.domain.Contact;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Contact} entity.
 */
public interface ContactSearchRepository extends ElasticsearchRepository<Contact, Long> {
}
