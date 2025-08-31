package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.TagRequest;
import com.example.minimal_prod_backend.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();
    Tag createTag(TagRequest tagRequest);
}
