package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.PoliticaRequest;
import com.example.minimal_prod_backend.entity.Politica;
import com.example.minimal_prod_backend.service.PolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping
    @PreAuthorize("@customSecurity.hasPermission('TAG_POLICIES', 'READ')")
    public List<Politica> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @PostMapping
    @PreAuthorize("@customSecurity.hasPermission('TAG_POLICIES', 'CREATE')")
    public ResponseEntity<Politica> createPolicy(@Valid @RequestBody PoliticaRequest request) {
        return new ResponseEntity<>(policyService.createPolicy(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@customSecurity.hasPermission('TAG_POLICIES', 'DELETE')")
    public void deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
    }
}