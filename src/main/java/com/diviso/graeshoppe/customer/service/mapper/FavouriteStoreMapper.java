package com.diviso.graeshoppe.customer.service.mapper;

import com.diviso.graeshoppe.customer.domain.*;
import com.diviso.graeshoppe.customer.service.dto.FavouriteStoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FavouriteStore} and its DTO {@link FavouriteStoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface FavouriteStoreMapper extends EntityMapper<FavouriteStoreDTO, FavouriteStore> {

    @Mapping(source = "customer.id", target = "customerId")
    FavouriteStoreDTO toDto(FavouriteStore favouriteStore);

    @Mapping(source = "customerId", target = "customer")
    FavouriteStore toEntity(FavouriteStoreDTO favouriteStoreDTO);

    default FavouriteStore fromId(Long id) {
        if (id == null) {
            return null;
        }
        FavouriteStore favouriteStore = new FavouriteStore();
        favouriteStore.setId(id);
        return favouriteStore;
    }
}
