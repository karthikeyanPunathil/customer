package com.diviso.graeshoppe.customer.web.rest;

import com.diviso.graeshoppe.customer.service.FavouriteStoreService;
import com.diviso.graeshoppe.customer.web.rest.errors.BadRequestAlertException;
import com.diviso.graeshoppe.customer.service.dto.FavouriteStoreDTO;

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
 * REST controller for managing {@link com.diviso.graeshoppe.customer.domain.FavouriteStore}.
 */
@RestController
@RequestMapping("/api")
public class FavouriteStoreResource {

    private final Logger log = LoggerFactory.getLogger(FavouriteStoreResource.class);

    private static final String ENTITY_NAME = "customerFavouriteStore";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FavouriteStoreService favouriteStoreService;

    public FavouriteStoreResource(FavouriteStoreService favouriteStoreService) {
        this.favouriteStoreService = favouriteStoreService;
    }

    /**
     * {@code POST  /favourite-stores} : Create a new favouriteStore.
     *
     * @param favouriteStoreDTO the favouriteStoreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new favouriteStoreDTO, or with status {@code 400 (Bad Request)} if the favouriteStore has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/favourite-stores")
    public ResponseEntity<FavouriteStoreDTO> createFavouriteStore(@Valid @RequestBody FavouriteStoreDTO favouriteStoreDTO) throws URISyntaxException {
        log.debug("REST request to save FavouriteStore : {}", favouriteStoreDTO);
        if (favouriteStoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new favouriteStore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FavouriteStoreDTO result = favouriteStoreService.save(favouriteStoreDTO);
        return ResponseEntity.created(new URI("/api/favourite-stores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /favourite-stores} : Updates an existing favouriteStore.
     *
     * @param favouriteStoreDTO the favouriteStoreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated favouriteStoreDTO,
     * or with status {@code 400 (Bad Request)} if the favouriteStoreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the favouriteStoreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/favourite-stores")
    public ResponseEntity<FavouriteStoreDTO> updateFavouriteStore(@Valid @RequestBody FavouriteStoreDTO favouriteStoreDTO) throws URISyntaxException {
        log.debug("REST request to update FavouriteStore : {}", favouriteStoreDTO);
        if (favouriteStoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FavouriteStoreDTO result = favouriteStoreService.save(favouriteStoreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, favouriteStoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /favourite-stores} : get all the favouriteStores.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of favouriteStores in body.
     */
    @GetMapping("/favourite-stores")
    public ResponseEntity<List<FavouriteStoreDTO>> getAllFavouriteStores(Pageable pageable) {
        log.debug("REST request to get a page of FavouriteStores");
        Page<FavouriteStoreDTO> page = favouriteStoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /favourite-stores/:id} : get the "id" favouriteStore.
     *
     * @param id the id of the favouriteStoreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the favouriteStoreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/favourite-stores/{id}")
    public ResponseEntity<FavouriteStoreDTO> getFavouriteStore(@PathVariable Long id) {
        log.debug("REST request to get FavouriteStore : {}", id);
        Optional<FavouriteStoreDTO> favouriteStoreDTO = favouriteStoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(favouriteStoreDTO);
    }

    /**
     * {@code DELETE  /favourite-stores/:id} : delete the "id" favouriteStore.
     *
     * @param id the id of the favouriteStoreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/favourite-stores/{id}")
    public ResponseEntity<Void> deleteFavouriteStore(@PathVariable Long id) {
        log.debug("REST request to delete FavouriteStore : {}", id);
        favouriteStoreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/favourite-stores?query=:query} : search for the favouriteStore corresponding
     * to the query.
     *
     * @param query the query of the favouriteStore search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/favourite-stores")
    public ResponseEntity<List<FavouriteStoreDTO>> searchFavouriteStores(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of FavouriteStores for query {}", query);
        Page<FavouriteStoreDTO> page = favouriteStoreService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
