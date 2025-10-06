package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.entity.Tag;
import com.example.minimal_prod_backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    @PreAuthorize("@customSecurity.hasPermission('TAGS_TAG', 'READ')")
    public List<Tag> list() {
        return tagService.getAllTags();
    }

}
