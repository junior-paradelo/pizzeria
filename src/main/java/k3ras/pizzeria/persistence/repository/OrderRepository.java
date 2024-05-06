package k3ras.pizzeria.persistence.repository;

import k3ras.pizzeria.persistence.entity.OrderEntity;
import k3ras.pizzeria.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String customerId);

    @Query(value = """
            SELECT
                po.id_order as idOrder,
                cu.name as customerName,
                po.date as orderDate,
                po.total as orderTotal,
                GROUP_CONCAT(pi.name) as pizzaNames
            FROM
                pizza_order po
                    INNER JOIN customer cu ON po.id_customer = cu.id_customer
                    INNER JOIN order_item oi ON po.id_order = oi.id_order
                    INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza
            WHERE
                    po.id_order = :orderId
            GROUP BY
                po.id_order,
                cu.name,
                po.date,
                po.total;
            """, nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") int orderId);
}
