package com.iaschowrai.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;



public record ProductResponse (String id,  String name, String description, BigDecimal price){
}
