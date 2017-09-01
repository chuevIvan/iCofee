package com.icoffee.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "coffee")
public class CoffeeEntity extends BaseEntity{

    @Basic
    @Column(name = "name", nullable = false, unique = true, length = 32)
    private String name;

    @Basic
    @Column(name = "price", nullable = false)
    private double price;

    public CoffeeEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Coffee: " +
                " id = " + getId() +
                ", name = " + name +
                ", price = " + price +
                ';';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoffeeEntity)) return false;
        if (!super.equals(o)) return false;

        CoffeeEntity that = (CoffeeEntity) o;

        return Double.compare(that.price, price) == 0 && (name != null ? name.equals(that.name) : that.name == null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
