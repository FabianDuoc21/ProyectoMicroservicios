package com.example.comuna.repository;

import com.example.comuna.model.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {
}
