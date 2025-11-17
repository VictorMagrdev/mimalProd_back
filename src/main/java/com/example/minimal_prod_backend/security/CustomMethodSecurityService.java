package com.example.minimal_prod_backend.security;

import com.example.minimal_prod_backend.entity.Rol;
import com.example.minimal_prod_backend.entity.Usuario;
import com.example.minimal_prod_backend.repository.PolicyRepository;
import com.example.minimal_prod_backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("customSecurity")
public class CustomMethodSecurityService {
    private final UserRepository userRepository;
    private final PolicyRepository policyRepository;

    public CustomMethodSecurityService(UserRepository userRepository, PolicyRepository policyRepository) {
        this.userRepository = userRepository;
        this.policyRepository = policyRepository;
    }

    public boolean hasPermission(String tagName, String permission) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                return false;
            }

            String username = authentication.getName();
            Usuario usuario = userRepository.findByUsername(username).orElse(null);

            if (usuario == null || !usuario.getActivo()) {
                return false;
            }

            List<Long> roleIds = usuario.getRoles().stream()
                    .map(Rol::getId)
                    .collect(Collectors.toList());

            if (roleIds.isEmpty()) {
                return false;
            }

            return policyRepository.existsByRol_IdInAndTag_NombreIgnoreCaseAndPermiso_Accion(
                    roleIds, tagName, permission);

        } catch (Exception e) {
            System.err.println("Error in permission check: " + e.getMessage());
            return false;
        }
    }
}
