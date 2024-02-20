package com.codigo.msrodriguezmijahuanga.infraestructure.mapper;

import com.codigo.msrodriguezmijahuanga.domain.aggregates.dto.PersonaDTO;
import com.codigo.msrodriguezmijahuanga.infraestructure.entity.PersonaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public PersonaDTO mapToDto(PersonaEntity personaEntity){
        return modelMapper.map(personaEntity, PersonaDTO.class);
    }

    public PersonaEntity mapToEntity(PersonaDTO personaDTO){
        return modelMapper.map(personaDTO, PersonaEntity.class );
    }
}
