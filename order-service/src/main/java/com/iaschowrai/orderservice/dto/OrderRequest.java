package com.iaschowrai.orderservice.dto;


import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;

    public List<OrderLineItemsDto> getOrderLineItemsDtoList() {
        return orderLineItemsDtoList;
    }

    public void setOrderLineItemsDtoList(List<OrderLineItemsDto> orderLineItemsDtoList) {
        this.orderLineItemsDtoList = orderLineItemsDtoList;
    }
}
