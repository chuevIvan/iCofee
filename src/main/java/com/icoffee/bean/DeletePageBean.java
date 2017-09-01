package com.icoffee.bean;

import com.icoffee.dao.OrderDao;
import com.icoffee.entity.OrderEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "deletePageView")
@SessionScoped
public class DeletePageBean {

    @ManagedProperty("#{orderDao}")
    private OrderDao orderDao;

    private OrderEntity orderEntity;

    public void deleteOrder(){
        orderDao.delete(orderEntity);
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
