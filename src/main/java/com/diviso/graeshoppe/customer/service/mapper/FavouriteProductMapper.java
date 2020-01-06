package com.diviso.graeshoppe.customer.service.mapper;

import com.diviso.graeshoppe.customer.domain.*;
import com.diviso.graeshoppe.customer.service.dto.FavouriteProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FavouriteProduct} and its DTO {@link FavouriteProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface FavouriteProductMapper extends EntityMapper<FavouriteProductDTO, FavouriteProduct> {

    @Mapping(source = "customer.id", target = "customerId")
    FavouriteProductDTO toDto(FavouriteProduct favouriteProduct);

    @Mapping(source = "customerId", target = "customer")
    FavouriteProduct toEntity(FavouriteProductDTO favouriteProductDTO);

    default FavouriteProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        FavouriteProduct favouriteProduct = new FavouriteProduct();
        favouriteProduct.setId(id);
        return favouriteProduct;
    }
}
