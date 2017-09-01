package com.icoffee.dao.impl;

import com.icoffee.dao.DeliveryDao;
import com.icoffee.entity.DeliveryEntity;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "deliveryDao")
@ApplicationScoped
public class DeliveryDaoImpl extends GenericDaoImpl<DeliveryEntity, Long> implements DeliveryDao {
    public DeliveryDaoImpl() {
        super(DeliveryEntity.class);
    }
}
