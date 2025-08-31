package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.TagRequest;
import com.example.minimal_prod_backend.entity.Role;
import com.example.minimal_prod_backend.entity.Tag;
import com.example.minimal_prod_backend.repository.RoleRepository;
import com.example.minimal_prod_backend.repository.TagRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagRepository repo;
    private final RoleRepository roleRepo;

    public TagController(TagRepository repo, RoleRepository roleRepo) {
        this.repo = repo;
        this.roleRepo = roleRepo;
    }

    @GetMapping
    public List<Tag> list() { return repo.findAll(); }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TagRequest r) {
        Tag t = Tag.builder().name(r.getName()).description(r.getDescription()).build();
        if (r.getOwnerRoleId() != null) {
            Role owner = roleRepo.findById(r.getOwnerRoleId()).orElse(null);
            t.setOwnerRole(owner);
        }
        repo.save(t);
        return ResponseEntity.ok(t);
    }

}
