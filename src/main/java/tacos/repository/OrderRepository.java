package tacos.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tacos.data.TacoOrder;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryNameAndPlacedAtBetween(String deliveryName, Date date1, Date date2);
//    List<TacoOrder> findByDeliveryZip(String deliveryZip);
//    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
//            String deliveryZip, Date startDate, Date endDate);
//
//    List<TacoOrder> findByDeliveryToAndDeliveryCityAllIgnoresCase(
//            String deliveryTo, String deliveryCity);
//
//    List<TacoOrder> findByDeliveryCityOrderByDeliveryTo(String city);
//
//    @Query("Order o where o.deliveryCity='Seattle'")
//    List<TacoOrder> readOrdersDeliveredInSeattle();
}
