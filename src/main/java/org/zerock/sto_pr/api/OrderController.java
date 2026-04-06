package org.zerock.sto_pr.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.zerock.sto_pr.domain.order.dto.OrderCreateRequest;
import org.zerock.sto_pr.domain.order.service.OrderCreateService;
import org.zerock.sto_pr.domain.trade.service.MatchingService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderCreateService orderCreateService;
    private final MatchingService mathcingService;


    public OrderController(OrderCreateService orderCreateService, MatchingService mathcingService) {
        this.orderCreateService = orderCreateService;
        this.mathcingService = mathcingService;
    }

    @PostMapping
    public Long create(@Valid @RequestBody OrderCreateRequest request) {
        return orderCreateService.create(request);
    }

    @PostMapping("/match/{tokenId}")
    public Long match(@PathVariable Long tokenId) {
        return mathcingService.matchOne(tokenId);
    }
}
