package com.codigo.msrodriguezmijahuanga.domain.ports.in;

import com.codigo.msrodriguezmijahuanga.domain.aggregates.dto.PersonaDTO;
import com.codigo.msrodriguezmijahuanga.domain.aggregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    PersonaDTO crearPersonaIn(RequestPersona requestPersona);
    Optional<PersonaDTO> obtenerPersonaIn(String numDoc);
    List<PersonaDTO> obtenerTodosIn();
    PersonaDTO actualizarIn(Long id, RequestPersona requestPersona);
    PersonaDTO deleteIn(Long id);
}
