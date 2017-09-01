package com.icoffee.dao.impl;

import com.icoffee.dao.OrderDao;
import com.icoffee.entity.OrderEntity;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "orderDao")
@ApplicationScoped
public class OrderDaoImpl extends GenericDaoImpl<OrderEntity, Long> implements OrderDao {
    public OrderDaoImpl() {
        super(OrderEntity.class);
    }
}
