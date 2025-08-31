package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
}