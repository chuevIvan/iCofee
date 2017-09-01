package com.icoffee.bean;

import com.icoffee.dao.OrderDao;
import com.icoffee.entity.OrderEntity;
import com.icoffee.utils.AppMessageUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "editPageBean")
@SessionScoped
public class EditPageBean {

    @ManagedProperty("#{orderDao}")
    private OrderDao orderDao;

    /**
     *  Метод для отображения сообщения об успешном удалении заказа
     *  Для передачи данных между страницами используем f:param
     */
    public void showMessage(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String data =  params.get("paramDel");
        AppMessageUtil appMessageUtil = AppMessageUtil.getInstance();
        appMessageUtil.showMessage(FacesMessage.SEVERITY_INFO, "Delete order", data);
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<OrderEntity> getOrderEntityList() {
        return orderDao.getAll();
    }
}
