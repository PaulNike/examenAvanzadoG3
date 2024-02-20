package com.codigo.msrodriguezmijahuanga.domain.ports.out;

import com.codigo.msrodriguezmijahuanga.domain.aggregates.dto.PersonaDTO;
import com.codigo.msrodriguezmijahuanga.domain.aggregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceOut {
    PersonaDTO crearPersonaOut(RequestPersona requestPersona);
    Optional<PersonaDTO> obtenerPersonaOut(String numDoc);
    List<PersonaDTO> obtenerTodosOut();
    PersonaDTO actualizarOut(Long id, RequestPersona requestPersona);
    PersonaDTO deleteOut(Long id);
}
