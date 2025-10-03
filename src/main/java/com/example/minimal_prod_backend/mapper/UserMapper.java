package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.RolResponse;
import com.example.minimal_prod_backend.dto.UserResponse;
import com.example.minimal_prod_backend.entity.Rol;
import com.example.minimal_prod_backend.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRoles")
    UserResponse toUserResponse(Usuario usuario);

    @Named("mapRoles")
    default Set<RolResponse> mapRoles(Set<Rol> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(role -> new RolResponse(
                        role.getId(),
                        role.getNombre(),
                        role.getDescripcion()
                ))
                .collect(Collectors.toSet());
    }

    RolResponse toRoleResponse(Rol rol);
}
