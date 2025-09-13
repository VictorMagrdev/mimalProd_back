package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstadoOrdenRequest;
import com.example.minimal_prod_backend.dto.EstadoOrdenResponse;
import com.example.minimal_prod_backend.entity.EstadoOrden;
import org.springframework.stereotype.Component;

@Component
public class EstadoOrdenMapper {

    public EstadoOrdenResponse toResponse(EstadoOrden entity) {
        if (entity == null) {
            return null;
        }
        EstadoOrdenResponse dto = new EstadoOrdenResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setActivo(entity.isActivo());
        dto.setCreadoEn(entity.getCreadoEn());
        return dto;
    }

    public EstadoOrden toEntity(EstadoOrdenRequest dto) {
        if (dto == null) {
            return null;
        }
        EstadoOrden entity = new EstadoOrden();
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setActivo(dto.getActivo());
        return entity;
    }

    public void updateEntityFromDto(EstadoOrdenRequest dto, EstadoOrden entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setActivo(dto.getActivo());
    }
}
