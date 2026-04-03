package org.zerock.sto_pr.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sto_pr.domain.order.entity.Order;
import org.zerock.sto_pr.domain.order.entity.OrderStatus;
import org.zerock.sto_pr.domain.order.entity.OrderType;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByToken_TokenIdAndOrderTypeAndOrderStatusOrderBySequenceAsc(
            Long tokenId, OrderType orderType, OrderStatus orderStatus
    );
}
