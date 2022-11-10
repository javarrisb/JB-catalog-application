package com.musicstore.catalog.repository;

import com.musicstore.catalog.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {
}
