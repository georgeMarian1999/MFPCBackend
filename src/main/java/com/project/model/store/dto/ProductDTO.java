package com.project.model.store.dto;



import com.project.model.store.Category;

import java.sql.Date;
import java.util.List;

public class ProductDTO {
    Integer id;
    String name;
    String description;
    String image;
    Boolean newProduct;
    Float price;
    Integer stock;
    Date addedDate;
    Integer sale;
    Integer stars;
    Category category;


    public ProductDTO(Integer id, String name, String description, String image, Boolean newProduct, Float price, Integer stock, Date addedDate, Integer sale, Integer stars, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.newProduct = newProduct;
        this.price = price;
        this.stock = stock;
        this.addedDate = addedDate;
        this.sale = sale;
        this.stars = stars;
        this.category = category;
    }

    public ProductDTO() {}

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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
