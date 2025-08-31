package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.TagRequest;
import com.example.minimal_prod_backend.entity.Tag;
import com.example.minimal_prod_backend.service.TagService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    @PreAuthorize("@customSecurity.hasPermission('TAG', 'READ')")
    public List<Tag> list() { 
        return tagService.getAllTags(); 
    }

    @PostMapping
    @PreAuthorize("@customSecurity.hasPermission('TAG', 'CREATE')")
    public ResponseEntity<Tag> create(@Valid @RequestBody TagRequest r) {
        Tag createdTag = tagService.createTag(r);
        return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
    }

}
