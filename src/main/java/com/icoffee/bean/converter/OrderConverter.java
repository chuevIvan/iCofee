package com.icoffee.bean.converter;

import com.icoffee.dao.OrderDao;
import com.icoffee.entity.OrderEntity;
import com.icoffee.utils.AppMessageUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "orderConverterBean")
@FacesConverter(value = "orderConverter")
public class OrderConverter implements Converter {

    @ManagedProperty("#{orderDao}")
    private OrderDao orderDao;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if(value == null || value.equals("")){
            return null;
        }

        long id;
        try{
            int firstIndex = value.indexOf("(");
            int lastIndex = value.indexOf(")");
            if(firstIndex > 0 && lastIndex > 0 && lastIndex > firstIndex){
                String idStr = value.substring(firstIndex, lastIndex);
                idStr = idStr.replaceAll("\\( \\) ", "");
                id = Long.valueOf(idStr);
                return orderDao.read(id);
            }
        }
        catch (Exception e){
            AppMessageUtil.getInstance().showMessage(FacesMessage.SEVERITY_ERROR, "Error converting Order", "Invalid format");
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return "";
        }
        try{
            if(value instanceof OrderEntity){
                OrderEntity orderEntity = (OrderEntity)value;
                return orderEntity.toString();
            }
            else {
                return (String)value;
            }
        }
        catch (Exception e){
            AppMessageUtil.getInstance().showMessage(FacesMessage.SEVERITY_ERROR, "Error converting Order", "Invalid format");
            return "";
        }
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
