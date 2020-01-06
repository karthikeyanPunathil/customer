package com.diviso.graeshoppe.customer.web.rest;

import com.diviso.graeshoppe.customer.service.FavouriteProductService;
import com.diviso.graeshoppe.customer.web.rest.errors.BadRequestAlertException;
import com.diviso.graeshoppe.customer.service.dto.FavouriteProductDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.diviso.graeshoppe.customer.domain.FavouriteProduct}.
 */
@RestController
@RequestMapping("/api")
public class FavouriteProductResource {

    private final Logger log = LoggerFactory.getLogger(FavouriteProductResource.class);

    private static final String ENTITY_NAME = "customerFavouriteProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FavouriteProductService favouriteProductService;

    public FavouriteProductResource(FavouriteProductService favouriteProductService) {
        this.favouriteProductService = favouriteProductService;
    }

    /**
     * {@code POST  /favourite-products} : Create a new favouriteProduct.
     *
     * @param favouriteProductDTO the favouriteProductDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new favouriteProductDTO, or with status {@code 400 (Bad Request)} if the favouriteProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/favourite-products")
    public ResponseEntity<FavouriteProductDTO> createFavouriteProduct(@Valid @RequestBody FavouriteProductDTO favouriteProductDTO) throws URISyntaxException {
        log.debug("REST request to save FavouriteProduct : {}", favouriteProductDTO);
        if (favouriteProductDTO.getId() != null) {
            throw new BadRequestAlertException("A new favouriteProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FavouriteProductDTO result = favouriteProductService.save(favouriteProductDTO);
        return ResponseEntity.created(new URI("/api/favourite-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /favourite-products} : Updates an existing favouriteProduct.
     *
     * @param favouriteProductDTO the favouriteProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated favouriteProductDTO,
     * or with status {@code 400 (Bad Request)} if the favouriteProductDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the favouriteProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/favourite-products")
    public ResponseEntity<FavouriteProductDTO> updateFavouriteProduct(@Valid @RequestBody FavouriteProductDTO favouriteProductDTO) throws URISyntaxException {
        log.debug("REST request to update FavouriteProduct : {}", favouriteProductDTO);
        if (favouriteProductDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FavouriteProductDTO result = favouriteProductService.save(favouriteProductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, favouriteProductDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /favourite-products} : get all the favouriteProducts.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of favouriteProducts in body.
     */
    @GetMapping("/favourite-products")
    public ResponseEntity<List<FavouriteProductDTO>> getAllFavouriteProducts(Pageable pageable) {
        log.debug("REST request to get a page of FavouriteProducts");
        Page<FavouriteProductDTO> page = favouriteProductService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /favourite-products/:id} : get the "id" favouriteProduct.
     *
     * @param id the id of the favouriteProductDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the favouriteProductDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/favourite-products/{id}")
    public ResponseEntity<FavouriteProductDTO> getFavouriteProduct(@PathVariable Long id) {
        log.debug("REST request to get FavouriteProduct : {}", id);
        Optional<FavouriteProductDTO> favouriteProductDTO = favouriteProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(favouriteProductDTO);
    }

    /**
     * {@code DELETE  /favourite-products/:id} : delete the "id" favouriteProduct.
     *
     * @param id the id of the favouriteProductDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/favourite-products/{id}")
    public ResponseEntity<Void> deleteFavouriteProduct(@PathVariable Long id) {
        log.debug("REST request to delete FavouriteProduct : {}", id);
        favouriteProductService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/favourite-products?query=:query} : search for the favouriteProduct corresponding
     * to the query.
     *
     * @param query the query of the favouriteProduct search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/favourite-products")
    public ResponseEntity<List<FavouriteProductDTO>> searchFavouriteProducts(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of FavouriteProducts for query {}", query);
        Page<FavouriteProductDTO> page = favouriteProductService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
