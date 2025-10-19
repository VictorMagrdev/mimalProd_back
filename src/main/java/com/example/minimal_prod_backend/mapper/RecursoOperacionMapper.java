package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.RecursoOperacionRequest;
import com.example.minimal_prod_backend.dto.RecursoOperacionResponse;
import com.example.minimal_prod_backend.entity.RecursoOperacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecursoOperacionMapper {

    @Mapping(target = "operacionOrdenId", source = "operacionOrden.id")
    RecursoOperacionResponse toResponse(RecursoOperacion recursoOperacion);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    @Mapping(target = "operacionOrden", ignore = true)
    RecursoOperacion toEntity(RecursoOperacionRequest recursoOperacionRequest);
}