package com.codigo.msrodriguezmijahuanga.domain.impl;

import com.codigo.msrodriguezmijahuanga.domain.aggregates.dto.PersonaDTO;
import com.codigo.msrodriguezmijahuanga.domain.aggregates.request.RequestPersona;
import com.codigo.msrodriguezmijahuanga.domain.ports.in.PersonaServiceIn;
import com.codigo.msrodriguezmijahuanga.domain.ports.out.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {

    @Autowired
    private PersonaServiceOut personaServiceOut;
    @Override
    public PersonaDTO crearPersonaIn(RequestPersona requestPersona) {
        return personaServiceOut.crearPersonaOut(requestPersona);
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(String numDoc) {
        return personaServiceOut.obtenerPersonaOut(numDoc);
    }

    @Override
    public List<PersonaDTO> obtenerTodosIn() {
        return personaServiceOut.obtenerTodosOut();
    }

    @Override
    public PersonaDTO actualizarIn(Long id, RequestPersona requestPersona) {
        return null;
    }

    @Override
    public PersonaDTO deleteIn(Long id) {
        return null;
    }

}
