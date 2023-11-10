/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author ingale.r
 */
public class MasterOrderList {
    
    ArrayList<Order> orderList;

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }
    
    public MasterOrderList(){
        orderList = new ArrayList<Order>();
    }
    
    public void addNewOrder(Order order){
        this.orderList.add(order);
    }
}
