package com.codigo.msascenciovilca2.infraestructure.dao;

import com.codigo.msascenciovilca2.infraestructure.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {
}
