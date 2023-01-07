/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.driver;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vikas_Singh
 */
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    
    public void addOrder(Order order)
    {
        this.orderRepository.addOrder(order);
    } 
    
    public void addPartner(String id) {
        this.orderRepository.addPartner(id);
    }
    
    public void addOrderPartnerPair(String orderId, String partnerId) {
        this.orderRepository.addOrderPartnerPair(orderId, partnerId);
    }
    
    public Order getOrderById(String id) {
        return this.orderRepository.getOrderById(id);
    }
    
    public DeliveryPartner getPartnerById(String id) {
        return this.orderRepository.getPartnerById(id);
    }
    
    public Integer getOrderCountByPartnerId(String id) {
        return this.orderRepository.getOrderCountByPartnerId(id);
    }
    
    public List<String> getOrdersByPartnerId(String id) {
        return this.orderRepository.getOrdersByPartnerId(id);
    }
    
    public List<String> getAllOrders() {
        return this.orderRepository.getAllOrders();
    }
    
    public Integer getCountOfUnassignedOrders() {
        return this.orderRepository.getCountOfUnassignedOrders();
    }
    
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        
        return this.orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }
    
    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return this.orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }
    
    public void deletePartnerById(String partnerId) {
         this.orderRepository.deletePartnerById(partnerId);
    }
    
    public void deleteOrderById(String orderId) {
        this.orderRepository.deleteOrderById(orderId);
        
    }
}
