package com.codigo.msrodriguezmijahuanga.infraestructure.repository;

import com.codigo.msrodriguezmijahuanga.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

    PersonaEntity findByNumDocu(@Param("numDocu") String numDocu);
    List<PersonaEntity> findByEstado(@Param("estado") Integer estado);
}
