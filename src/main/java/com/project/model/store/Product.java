package com.project.model.store;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String description;
    String image;
    Boolean newProduct;
    Float price;
    Integer stock;
    Date addedDate;
    Integer sale;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    Set<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "id_category")
    Category category;

    public Product() {

    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }


    public Product(Integer id, String name, String description, String image, Boolean newProduct, Float price, Integer stock, Date addedDate, Integer sale, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.newProduct = newProduct;
        this.price = price;
        this.stock = stock;
        this.addedDate = addedDate;
        this.sale = sale;
        this.category = category;
    }

    public Product(Integer id, String name, String description, String image, Boolean newProduct, Float price, Integer stock, Date addedDate, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.newProduct = newProduct;
        this.price = price;
        this.stock = stock;
        this.addedDate = addedDate;
        this.category = category;
        this.sale = 0;
    }
    public Product(String name, String description, String image, Boolean newProduct, Float price, Integer stock, Date addedDate, Category category) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.newProduct = newProduct;
        this.price = price;
        this.stock = stock;
        this.addedDate = addedDate;
        this.category = category;
        this.sale = 0;
    }

    public void Product() {}

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Boolean newProduct) {
        this.newProduct = newProduct;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", newProduct=" + newProduct +
                ", price=" + price +
                ", stock=" + stock +
                ", addedDate=" + addedDate +
                ", sale=" + sale +
                ", category=" + category +
                '}';
    }
}
