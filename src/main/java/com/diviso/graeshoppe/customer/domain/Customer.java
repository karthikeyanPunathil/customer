package com.diviso.graeshoppe.customer.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.completion.Completion;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @NotNull
    @Column(name = "idp_code", nullable = false)
    private String idpCode;

    @Column(name = "name")
    private String name;

    @Column(name = "idp_sub")
    private String idpSub;

    @Column(name = "customer_unique_id")
    private String customerUniqueId;

    @Column(name = "image_link")
    private String imageLink;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "loyalty_point")
    private Long loyaltyPoint;
    
 

    @OneToOne
    @JoinColumn(unique = true)
    private Contact contact;

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FavouriteStore> favouritestores = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FavouriteProduct> favouriteproducts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdpCode() {
        return idpCode;
    }

    public Customer idpCode(String idpCode) {
        this.idpCode = idpCode;
        return this;
    }

    public void setIdpCode(String idpCode) {
        this.idpCode = idpCode;
    }

    public String getName() {
        return name;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdpSub() {
        return idpSub;
    }

    public Customer idpSub(String idpSub) {
        this.idpSub = idpSub;
        return this;
    }

    public void setIdpSub(String idpSub) {
        this.idpSub = idpSub;
    }

    public String getCustomerUniqueId() {
        return customerUniqueId;
    }

    public Customer customerUniqueId(String customerUniqueId) {
        this.customerUniqueId = customerUniqueId;
        return this;
    }

    public void setCustomerUniqueId(String customerUniqueId) {
        this.customerUniqueId = customerUniqueId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Customer imageLink(String imageLink) {
        this.imageLink = imageLink;
        return this;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public byte[] getImage() {
        return image;
    }

    public Customer image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Customer imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Long getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public Customer loyaltyPoint(Long loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
        return this;
    }

    public void setLoyaltyPoint(Long loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }

    public Contact getContact() {
        return contact;
    }

    public Customer contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Set<FavouriteStore> getFavouritestores() {
        return favouritestores;
    }

    public Customer favouritestores(Set<FavouriteStore> favouriteStores) {
        this.favouritestores = favouriteStores;
        return this;
    }

    public Customer addFavouritestore(FavouriteStore favouriteStore) {
        this.favouritestores.add(favouriteStore);
        favouriteStore.setCustomer(this);
        return this;
    }

    public Customer removeFavouritestore(FavouriteStore favouriteStore) {
        this.favouritestores.remove(favouriteStore);
        favouriteStore.setCustomer(null);
        return this;
    }

    public void setFavouritestores(Set<FavouriteStore> favouriteStores) {
        this.favouritestores = favouriteStores;
    }

    public Set<FavouriteProduct> getFavouriteproducts() {
        return favouriteproducts;
    }

    public Customer favouriteproducts(Set<FavouriteProduct> favouriteProducts) {
        this.favouriteproducts = favouriteProducts;
        return this;
    }

    public Customer addFavouriteproduct(FavouriteProduct favouriteProduct) {
        this.favouriteproducts.add(favouriteProduct);
        favouriteProduct.setCustomer(this);
        return this;
    }

    public Customer removeFavouriteproduct(FavouriteProduct favouriteProduct) {
        this.favouriteproducts.remove(favouriteProduct);
        favouriteProduct.setCustomer(null);
        return this;
    }

    public void setFavouriteproducts(Set<FavouriteProduct> favouriteProducts) {
        this.favouriteproducts = favouriteProducts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", idpCode='" + getIdpCode() + "'" +
            ", name='" + getName() + "'" +
            ", idpSub='" + getIdpSub() + "'" +
            ", customerUniqueId='" + getCustomerUniqueId() + "'" +
            ", imageLink='" + getImageLink() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", loyaltyPoint=" + getLoyaltyPoint() +
            "}";
    }
}
