package com.diviso.graeshoppe.customer.repository;

import com.diviso.graeshoppe.customer.domain.FavouriteStore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FavouriteStore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FavouriteStoreRepository extends JpaRepository<FavouriteStore, Long> {

}
