package com.example.tiendaluz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDTO {
    private String token;
    private String info;
}
