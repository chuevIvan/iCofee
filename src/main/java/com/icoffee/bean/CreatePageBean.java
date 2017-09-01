package com.icoffee.bean;

import com.icoffee.dao.CoffeeDao;
import com.icoffee.dao.DeliveryDao;
import com.icoffee.dao.OrderDao;
import com.icoffee.entity.CoffeeEntity;
import com.icoffee.entity.DeliveryEntity;
import com.icoffee.entity.OrderEntity;
import com.icoffee.utils.DateUtil;
import com.icoffee.utils.MathMethodsUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.*;

@ManagedBean(name = "createPageBean")
@SessionScoped
public class CreatePageBean {

    /**
     *  минимальное колличество кофе для продажи
     */
    private final double MIN_COUNT_COFFEE = 0.1;

    @ManagedProperty("#{coffeeDao}")
    private CoffeeDao coffeeDao;
    @ManagedProperty("#{deliveryDao}")
    private DeliveryDao deliveryDao;
    @ManagedProperty("#{orderDao}")
    private OrderDao orderDao;

    private OrderEntity orderEntity;

    private List<DeliveryEntity> deliveryEntityList;
    private List<CoffeeEntity> coffeeEntityList;

    /**
     *  активная ячейка аккордеона отображающего CoffeeEntity
     */
    private int indexActiveAccordionPanel = 0;

    public CreatePageBean() {
    }

    /**
     *  место входа в бин
     *  к этому моменту при помощи f:setPropertyActionListener в случае редактирования
     *  задан orderEntity отличный от null
     */
    public void init(){
        loadData();
        if(orderEntity == null){
            createNewOrderEntity();
        }
    }

    /**
     *  обновление данных
     */
    private void loadData(){
        coffeeEntityList = coffeeDao.getAll();
        deliveryEntityList = deliveryDao.getAll();
    }

    private void createNewOrderEntity(){
        orderEntity = new OrderEntity();
        if(!deliveryEntityList.isEmpty()){
            orderEntity.setDeliveryEntity(deliveryEntityList.get(0));
        }
        if(!coffeeEntityList.isEmpty()){
            orderEntity.setCoffeeEntity(coffeeEntityList.get(0));
        }
        orderEntity.setCountCoffee(MIN_COUNT_COFFEE);
    }

    /**
     * получение стоимости за кофе, без учёта доставки
     */
    public double getFullPriceCoffee(){
        if(orderEntity.getCoffeeEntity() == null){
            return 0;
        }
        return MathMethodsUtil.roundDouble(orderEntity.getCountCoffee() * orderEntity.getCoffeeEntity().getPrice());
    }

    /**
     *  получение полной стоимости
     */
    public double getFullPrice(){
        if(orderEntity.getDeliveryEntity() == null){
            return getFullPriceCoffee();
        }
        return MathMethodsUtil.roundDouble(orderEntity.getDeliveryEntity().getPrice() + getFullPriceCoffee());
    }

    /**
     *  сохранение сущности при нажатии на кнопку save
     */
    public void saveCoffeeOrder() {
        if(orderEntity == null){
            return;
        }
        if(orderEntity.getId() == 0){
            orderDao.create(orderEntity);
        }
        else {
            orderDao.update(orderEntity);
        }
    }

    /**
     *  Получаем минимальное время для начала периода доставки.
     *  Для тестового проекта используем +2 часа от момента начала
     *  оформления заказа
     */
    public Date getMinStartDate(){
        return DateUtil.plusHours(null, 2);
    }

    /**
     *  Получаем минимальное время для окончания периода доставки.
     *  Для тестового проекта используем +1 час от начала
     *  периода доставки
     */
    public Date getMinEndDate(){
        Date startDate = orderEntity.getStartDate();
        if(startDate == null){
            return null;
        }
        return DateUtil.plusHours(startDate, 1);
    }

    /**
     *  Метод-валидатор для поля ввода колличества кофе
     *  (в соответствии с заданием
     */

    public void validateCount(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String msg = "Error validate count coffee field!";
        String desc = "Enter a value from 0,1 to 20 with a precision of hundredths";
        double countCoffee = 0;
        String s = value.toString();
        if (!s.matches("[0-9]+(\\.[0-9][0-9]?)?") && !s.matches("[0-9]+(\\,[0-9][0-9]?)?")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, desc));
        }
        countCoffee = (Double)value;
        if (countCoffee < 0.1 || countCoffee > 20) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, desc));
        }
    }

    /**
     *  Возвращаем индекс для панели аккардеона с CoffeeEntity
     */
    public int getIndexActiveAccordionPanel(){
        for(CoffeeEntity coffeeEntity: coffeeEntityList){
            if(orderEntity.getCoffeeEntity().getId() == coffeeEntity.getId()){
                indexActiveAccordionPanel = coffeeEntityList.indexOf(coffeeEntity);
                return indexActiveAccordionPanel;
            }
        }
        return 0;
    }

    public void setIndexActiveAccordionPanel(int indexActiveAccordionPanel) {
        this.indexActiveAccordionPanel = indexActiveAccordionPanel;
    }

    public List<CoffeeEntity> getCoffeeEntityList() {
        return coffeeEntityList;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setCoffeeEntityList(List<CoffeeEntity> coffeeEntityList) {
        this.coffeeEntityList = coffeeEntityList;
    }

    public List<DeliveryEntity> getDeliveryEntityList() {
        return deliveryEntityList;
    }

    public void setDeliveryEntityList(List<DeliveryEntity> deliveryEntityList) {
        this.deliveryEntityList = deliveryEntityList;
    }

    public void setCoffeeDao(CoffeeDao coffeeDao) {
        this.coffeeDao = coffeeDao;
    }

    public void setDeliveryDao(DeliveryDao deliveryDao) {
        this.deliveryDao = deliveryDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
