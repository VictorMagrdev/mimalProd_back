package com.example.minimal_prod_backend.dto;

import lombok.Data;

@Data
public class TagRequest {
    private String name;
    private String description;
    private Long ownerRoleId;
}