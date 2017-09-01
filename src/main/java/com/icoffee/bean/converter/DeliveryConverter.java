package com.icoffee.bean.converter;

import com.icoffee.bean.CreatePageBean;
import com.icoffee.entity.DeliveryEntity;
import com.icoffee.utils.AppMessageUtil;

import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;

@ManagedBean(name = "deliveryConverterBean")
@FacesConverter(value = "deliveryConverter")
public class DeliveryConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        try {
            ValueExpression vex =
                    context.getApplication().getExpressionFactory()
                            .createValueExpression(context.getELContext(),
                                    "#{createPageBean}", CreatePageBean.class);

            CreatePageBean createPageBean = (CreatePageBean)vex.getValue(context.getELContext());
            List<DeliveryEntity> deliveryEntityList = createPageBean.getDeliveryEntityList();
            for(DeliveryEntity deliveryEntity: deliveryEntityList){
                if(deliveryEntity.getId() == Long.valueOf(value)){
                    return deliveryEntity;
                }
            }
        } catch (Exception e) {
            AppMessageUtil.getInstance().showMessage(FacesMessage.SEVERITY_ERROR, "Error converting Delivery", "Invalid format");
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            DeliveryEntity deliveryEntity = (DeliveryEntity) value;
            return deliveryEntity.getId() + "";
        } catch (Exception e) {
            AppMessageUtil.getInstance().showMessage(FacesMessage.SEVERITY_ERROR, "Error converting Delivery", "Invalid format");
            return "";
        }
    }
}
