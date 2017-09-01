package com.icoffee.entity;

import com.icoffee.utils.MathMethodsUtil;
import com.icoffee.utils.DateUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`order`")
public class OrderEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "coffee_id", nullable = false)
    private CoffeeEntity coffeeEntity;

    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    private DeliveryEntity deliveryEntity;

    @Basic
    @Column(name = "count_coffee", nullable = false)
    private double countCoffee;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate", nullable = false)
    private Date endDate;

    public OrderEntity() {
    }

    public CoffeeEntity getCoffeeEntity() {
        return coffeeEntity;
    }

    public void setCoffeeEntity(CoffeeEntity coffeeEntity) {
        this.coffeeEntity = coffeeEntity;
    }

    public double getCountCoffee() {
        return countCoffee;
    }

    public void setCountCoffee(double countCoffee) {
        this.countCoffee = countCoffee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public DeliveryEntity getDeliveryEntity() {
        return deliveryEntity;
    }

    public void setDeliveryEntity(DeliveryEntity deliveryEntity) {
        this.deliveryEntity = deliveryEntity;
    }

    @Override
    public String toString() {
        return "Order (" + getId() + "):" +
                getCoffeeEntity().getName() +
                " count = " + getCountCoffee() +
                ", delivery = " + getDeliveryEntity().getName() + " (" + getDeliveryEntity().getPrice() + "$)" +
                ", from = " + DateUtil.getSimpleDateFormat().format(getStartDate()) +
                ", to = " + DateUtil.getSimpleDateFormat().format(getEndDate()) +
                ", full price = " + (MathMethodsUtil.roundDouble(getCountCoffee() * getCoffeeEntity().getPrice() + getDeliveryEntity().getPrice())) +
                "($);";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity)) return false;
        if (!super.equals(o)) return false;

        OrderEntity that = (OrderEntity) o;

        if (Double.compare(that.countCoffee, countCoffee) != 0) return false;
        if (coffeeEntity != null ? !coffeeEntity.equals(that.coffeeEntity) : that.coffeeEntity != null) return false;
        if (deliveryEntity != null ? !deliveryEntity.equals(that.deliveryEntity) : that.deliveryEntity != null)
            return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        return endDate != null ? endDate.equals(that.endDate) : that.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (coffeeEntity != null ? coffeeEntity.hashCode() : 0);
        result = 31 * result + (deliveryEntity != null ? deliveryEntity.hashCode() : 0);
        temp = Double.doubleToLongBits(countCoffee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
