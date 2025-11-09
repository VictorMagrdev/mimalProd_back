package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.TagRequest;
import com.example.minimal_prod_backend.entity.Tag;
import com.example.minimal_prod_backend.repository.RoleRepository;
import com.example.minimal_prod_backend.repository.TagRepository;
import com.example.minimal_prod_backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, RoleRepository roleRepository) {
        this.tagRepository = tagRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    @Transactional
    public Tag createTag(TagRequest r) {
        Tag t = Tag.builder()
                .nombre(r.nombre())
                .descripcion(r.descripcion())
                .build();

        return tagRepository.save(t);
    }
}
