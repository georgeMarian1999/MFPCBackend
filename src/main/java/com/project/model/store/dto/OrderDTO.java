package com.project.model.store.dto;


import com.project.model.store.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private Integer id;
    private String phone;
    private String street;
    private String apartment;
    private String city;
    private String county;
    private Long postcode;
    private Float subtotal;
    private Float taxes;
    private Float total;
    private OrderStatus orderStatus;
    private LocalDate date;
    private Float shipping;
    List<ProductOrderDTO> productOrderDTOList;

    public OrderDTO() {}

    public OrderDTO(Integer id, String phone, String street, String apartment, String city, String county, Long postcode, Float subtotal, Float taxes, Float total, OrderStatus orderStatus, LocalDate date, Float shipping, List<ProductOrderDTO> productOrderDTOList) {
        this.id = id;
        this.phone = phone;
        this.street = street;
        this.apartment = apartment;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
        this.subtotal = subtotal;
        this.taxes = taxes;
        this.total = total;
        this.orderStatus = orderStatus;
        this.date = date;
        this.shipping = shipping;
        this.productOrderDTOList = productOrderDTOList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Long getPostcode() {
        return postcode;
    }

    public void setPostcode(Long postcode) {
        this.postcode = postcode;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getTaxes() {
        return taxes;
    }

    public void setTaxes(Float taxes) {
        this.taxes = taxes;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getShipping() {
        return shipping;
    }

    public void setShipping(Float shipping) {
        this.shipping = shipping;
    }

    public List<ProductOrderDTO> getProductOrderDTOList() {
        return productOrderDTOList;
    }

    public void setProductOrderDTOList(List<ProductOrderDTO> productOrderDTOList) {
        this.productOrderDTOList = productOrderDTOList;
    }
}
