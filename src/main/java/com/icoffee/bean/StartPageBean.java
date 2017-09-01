package com.icoffee.bean;

import com.icoffee.dao.OrderDao;
import com.icoffee.entity.OrderEntity;
import com.icoffee.utils.AppMessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.util.List;

@ManagedBean(name = "startPageBean")
@SessionScoped
public class StartPageBean {

    @ManagedProperty("#{orderDao}")
    private OrderDao orderDao;

    @PostConstruct
    private void init(){
    }

    public void showMessage(ActionEvent event) {
        String typeMessage = (String)event.getComponent().getAttributes().get("typeMessage");
        AppMessageUtil appMessageUtil = AppMessageUtil.getInstance();
        appMessageUtil.showStartPageMessage(typeMessage);
    }

    public List<OrderEntity> getOrderEntityList() {
        return orderDao.getAll();
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
