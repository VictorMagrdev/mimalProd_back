package com.example.minimal_prod_backend.security;

import com.example.minimal_prod_backend.entity.Role;
import com.example.minimal_prod_backend.entity.User;
import com.example.minimal_prod_backend.repository.PolicyRepository;
import com.example.minimal_prod_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("customSecurity")
public class CustomMethodSecurityService {

    private final UserRepository userRepository;
    private final PolicyRepository policyRepository;

    @Autowired
    public CustomMethodSecurityService(UserRepository userRepository, PolicyRepository policyRepository) {
        this.userRepository = userRepository;
        this.policyRepository = policyRepository;
    }

    public boolean hasPermission(String tagName, String permission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return false;
        }

        List<Long> roleIds = user.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toList());

        if (roleIds.isEmpty()) {
            return false;
        }

        return policyRepository.existsByRole_IdInAndTag_NameAndPermission_Action(roleIds, tagName, permission);
    }
}

