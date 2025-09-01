package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.PolicyRequest;
import com.example.minimal_prod_backend.entity.Policy;
import com.example.minimal_prod_backend.service.PolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    @Autowired
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping
    @PreAuthorize("@customSecurity.hasPermission('TAG_POLICIES', 'CREATE')")
    public ResponseEntity<Policy> createPolicy(@Valid @RequestBody PolicyRequest request) {
        return new ResponseEntity<>(policyService.createPolicy(request), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("@customSecurity.hasPermission('TAG_POLICIES', 'READ')")
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@customSecurity.hasPermission('TAG_POLICIES', 'DELETE')")
    public void deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
    }
}
