package com.lym.service;

import java.util.List;

import com.lym.entity.Order;
import com.lym.entity.PageBean;

public interface OrderService {
	
	public void saveOrder(Order order);
	
	public List<Order> findOrder(Order s_order,PageBean pageBean);
	
	public Long getOrderCount(Order s_order);
	
	public void updateOrderStatus(int status,String orderNo);
	
	public Order getOrderById(int orderId);
}
