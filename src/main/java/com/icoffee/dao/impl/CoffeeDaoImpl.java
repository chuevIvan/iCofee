package com.icoffee.dao.impl;

import com.icoffee.dao.CoffeeDao;
import com.icoffee.entity.CoffeeEntity;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "coffeeDao")
@ApplicationScoped
public class CoffeeDaoImpl extends GenericDaoImpl<CoffeeEntity, Long> implements CoffeeDao{
    public CoffeeDaoImpl() {
        super(CoffeeEntity.class);
    }
}
