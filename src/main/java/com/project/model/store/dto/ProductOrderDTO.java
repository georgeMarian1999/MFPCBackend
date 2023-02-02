package com.project.model.store.dto;

public class ProductOrderDTO {
    Integer id;
    String name;
    Float price;
    Integer quantity;
    Integer sale;

    public ProductOrderDTO(Integer id, String name, Float price, Integer quantity, Integer sale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sale = sale;
    }

    public ProductOrderDTO(Integer id, String name, Float price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductOrderDTO() {}

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }
}
