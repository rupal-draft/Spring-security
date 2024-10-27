package com.springsecurity.Spring.Security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class FlatDto {
    private String name;
    private String destination;
    private Integer price;
}
