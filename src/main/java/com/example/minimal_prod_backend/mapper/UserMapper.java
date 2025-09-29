package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.RoleResponse;
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
    default Set<RoleResponse> mapRoles(Set<Rol> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(role -> {
                    RoleResponse r = new RoleResponse();
                    r.setId(role.getId());
                    r.setNombre(role.getNombre());
                    r.setDescripcion(role.getDescripcion());
                    return r;
                })
                .collect(Collectors.toSet());
    }

    @Mapping(target = "roles", ignore = true)
        // para crear/update no usamos roles aquí
    Usuario toUsuario(UserResponse response);

    RoleResponse toRoleResponse(Rol rol);
}
