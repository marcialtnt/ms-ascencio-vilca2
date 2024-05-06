package com.codigo.msascenciovilca2.infraestructure.dao;

import com.codigo.msascenciovilca2.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
}
