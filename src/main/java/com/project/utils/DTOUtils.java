package com.project.utils;


import com.project.model.store.*;
import com.project.model.store.dto.OrderDTO;
import com.project.model.store.dto.ProductDTO;
import com.project.model.store.dto.ProductOrderDTO;
import com.project.model.store.dto.UserDTO;

import java.util.List;

public class DTOUtils {
    public static UserDTO userToDto(User user) {
        UserDTO udto = new UserDTO();
        udto.setId(user.getId());
        udto.setEmail(user.getEmail());
        udto.setName(user.getName());
        udto.setUsername(user.getUsername());
        udto.setPassword(user.getPassword());
        if (user.getRole() != null) {
            udto.setRole(user.getRole()
                    .getName());
        }
        return udto;
    }

    public static ProductDTO productToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        productDTO.setNewProduct(product.getNewProduct());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        productDTO.setAddedDate(product.getAddedDate());
        productDTO.setSale(product.getSale());
        productDTO.setCategory(product.getCategory());
        return productDTO;
    }

    public static ProductOrderDTO productOrderToDto(Product product, OrderDetail orderDetail) {
        ProductOrderDTO productOrderDTO = new ProductOrderDTO();
        productOrderDTO.setId(product.getId());
        productOrderDTO.setName(product.getName());
        productOrderDTO.setPrice(product.getPrice());
        productOrderDTO.setQuantity(orderDetail.getQuantity());
        productOrderDTO.setSale(orderDetail.getProduct().getSale());
        return productOrderDTO;
    }

    public static OrderDTO orderToDto(Order order, List<ProductOrderDTO> productOrderDTOList) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setPhone(order.getPhone());
        orderDTO.setStreet(order.getStreet());
        orderDTO.setApartment(order.getApartment());
        orderDTO.setCity(order.getCity());
        orderDTO.setCounty(order.getCounty());
        orderDTO.setPostcode(order.getPostcode());
        orderDTO.setSubtotal(order.getSubtotal());
        orderDTO.setTaxes(order.getTaxes());
        orderDTO.setTotal(order.getTotal());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setDate(order.getDate());
        orderDTO.setShipping(order.getShipping());
        orderDTO.setProductOrderDTOList(productOrderDTOList);
        return orderDTO;
    }

}
