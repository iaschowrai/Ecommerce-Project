package com.iaschowrai.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


public record ProductRequest(String id, String name, String description, BigDecimal price) {

    }
