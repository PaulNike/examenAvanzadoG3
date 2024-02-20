package com.codigo.msrodriguezmijahuanga.infraestructure.repository;

import com.codigo.msrodriguezmijahuanga.infraestructure.entity.TipoPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TipoPersonaRepository extends JpaRepository<TipoPersonaEntity, Long> {
    TipoPersonaEntity findByCodTipo(@Param("x") String codTipo);
}
