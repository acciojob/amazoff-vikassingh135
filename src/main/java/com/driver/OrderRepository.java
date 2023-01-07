/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vikas_Singh
 */
@Repository
public class OrderRepository {
    
    HashMap<String,Order> orderMap = new HashMap<>();
    HashMap<String,DeliveryPartner> delMap = new HashMap<>();
    HashMap<String, String> orderPartnerMap = new HashMap<>();
    HashMap<String, List<String>> partnerOrdersMap = new HashMap<>(); 
    
    
    public void addOrder(Order order)
    {
        orderMap.put(order.getId(), order);
    } 
    
    public void addPartner(String id) {
        delMap.put(id, new DeliveryPartner(id));
    }
    
    public void addOrderPartnerPair(String orderId, String partnerId) {
        orderPartnerMap.put(orderId, partnerId);
        if(!partnerOrdersMap.containsKey(partnerId)) {
            partnerOrdersMap.put(partnerId, new ArrayList<>());
        }
        partnerOrdersMap.get(partnerId).add(orderId);
    }
    
    public Order getOrderById(String id) {
        return orderMap.get(id);
    }
    
    public DeliveryPartner getPartnerById(String id) {
        return delMap.get(id);
    }
    
    public Integer getOrderCountByPartnerId(String id) {
        return partnerOrdersMap.get(id).size();
    }
    
    public List<String> getOrdersByPartnerId(String id) {
        return partnerOrdersMap.get(id);
    }
    
    public List<String> getAllOrders() {
        return new ArrayList<>(orderMap.keySet());
    }
    
    public Integer getCountOfUnassignedOrders() {
        int ans = 0;
        return orderMap.size() - orderPartnerMap.size();
    }
    
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        
        int ans = 0;
        
        for(String orderId : partnerOrdersMap.get(partnerId)) {
            if(orderMap.get(orderId).getDeliveryTime() > stringToTime(time)) ans++;
        }
        
        return ans;
    }
    
    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        int lastDelTime = 0;
        for(String orderId : partnerOrdersMap.get(this)) {
            Order o = orderMap.get(orderId);
            lastDelTime = Math.max(lastDelTime, o.getDeliveryTime());
        }
        return timeToString(lastDelTime);
    }
    
    public void deletePartnerById(String partnerId) {
        for(String orderId : partnerOrdersMap.get(partnerId)) {
            Order o = orderMap.get(orderId);
            orderPartnerMap.remove(o);
            orderMap.remove(o);
        }
        partnerOrdersMap.remove(partnerId);
    }
    
    public void deleteOrderById(String orderId) {
        for(String i : orderPartnerMap.keySet()) {
            if(i.equals(orderId)) {
                orderPartnerMap.remove(i);
            }
        }
        
        for(String i : partnerOrdersMap.keySet()) {
            for(String j : partnerOrdersMap.get(i)) {
                if(j.equals(orderId)) partnerOrdersMap.get(i).remove(j);
            }
        }
        
        orderMap.remove(orderId);
        
    }
    
    public static String timeToString(Integer time) {
        return String.format("%0.2d:%0.2d", time/60, time%60);
    }
    
    public static int stringToTime(String time) {
        int h = Integer.parseInt(time.substring(0,2));
        int m = Integer.parseInt(time.substring(3));
        return (h*60)+m;
    }
}
