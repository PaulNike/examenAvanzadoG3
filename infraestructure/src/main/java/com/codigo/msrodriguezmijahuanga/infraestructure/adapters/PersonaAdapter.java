package com.codigo.msrodriguezmijahuanga.infraestructure.adapters;

import com.codigo.msrodriguezmijahuanga.domain.aggregates.constants.Constants;
import com.codigo.msrodriguezmijahuanga.domain.aggregates.dto.PersonaDTO;
import com.codigo.msrodriguezmijahuanga.domain.aggregates.request.RequestPersona;
import com.codigo.msrodriguezmijahuanga.domain.aggregates.response.ResposeReniec;
import com.codigo.msrodriguezmijahuanga.domain.ports.out.PersonaServiceOut;
import com.codigo.msrodriguezmijahuanga.infraestructure.entity.PersonaEntity;
import com.codigo.msrodriguezmijahuanga.infraestructure.entity.TipoDocumentoEntity;
import com.codigo.msrodriguezmijahuanga.infraestructure.entity.TipoPersonaEntity;
import com.codigo.msrodriguezmijahuanga.infraestructure.mapper.PersonaMapper;
import com.codigo.msrodriguezmijahuanga.infraestructure.repository.PersonaRepository;
import com.codigo.msrodriguezmijahuanga.infraestructure.repository.TipoDocumentoRepository;
import com.codigo.msrodriguezmijahuanga.infraestructure.repository.TipoPersonaRepository;
import com.codigo.msrodriguezmijahuanga.infraestructure.rest.client.ClienteReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoPersonaRepository tipoPersonaRepository;
    private final ClienteReniec reniec;
    private final PersonaMapper personaMapper;

    @Value("${token.api}")
    private String tokenApi;
    @Override
    public PersonaDTO crearPersonaOut(RequestPersona requestPersona) {
        ResposeReniec resposeReniec = getInfo(requestPersona.getNumDoc());
        personaRepository.save(getEntity(resposeReniec,requestPersona));
        return personaMapper.mapToDto(getEntity(resposeReniec,requestPersona));
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaOut(String numDoc) {
        return Optional.of(personaMapper.mapToDto(personaRepository.findByNumDocu(numDoc)));
    }

    @Override
    public List<PersonaDTO> obtenerTodosOut() {
        List<PersonaDTO> personaDTOS = new ArrayList<>();
        List<PersonaEntity> entityList = personaRepository.findByEstado(Constants.STATUS_ACTIVE);
        for(PersonaEntity entity : entityList){
            PersonaDTO personaDTO = personaMapper.mapToDto(entity);
            personaDTOS.add(personaDTO);
        }
        return personaDTOS;
    }

    @Override
    public PersonaDTO actualizarOut(Long id, RequestPersona requestPersona) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> personaRecuperada = personaRepository.findById(id);
            ResposeReniec resposeReniec = getInfo(requestPersona.getNumDoc());
            personaRepository.save(getEntityUpdate(resposeReniec, personaRecuperada.get()));
            return personaMapper.mapToDto(getEntityUpdate(resposeReniec,personaRecuperada.get()));
        }
        return null;
    }

    @Override
    public PersonaDTO deleteOut(Long id) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> personaRecuperada = personaRepository.findById(id);
            personaRecuperada.get().setEstado(Constants.STATUS_INACTIVE);
            personaRecuperada.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            personaRecuperada.get().setDateDelet(getTimestamp());
            personaRepository.save(personaRecuperada.get());
            return personaMapper.mapToDto(personaRecuperada.get());
        }
        return null;
    }

    private ResposeReniec getInfo(String numero){
        String autho = "Bearer "+tokenApi;
        return reniec.getInfoReniec(numero,autho);
    }

    private PersonaEntity getEntity(ResposeReniec reniec, RequestPersona requestPersona){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestPersona.getTipoDOc());
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipo(requestPersona.getTipoPer());
        PersonaEntity entity = new PersonaEntity();
        entity.setNumDocu(reniec.getNumeroDocumento());
        entity.setNombres(reniec.getNombres());
        entity.setApePat(reniec.getApellidoPaterno());
        entity.setApeMat(reniec.getApellidoMaterno());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setTipoDocumento(tipoDocumento);
        entity.setTipoPersona(tipoPersona);
        return entity;
    }

    private PersonaEntity getEntityUpdate(ResposeReniec reniec, PersonaEntity personaActualizar){
        personaActualizar.setNombres(reniec.getNombres());
        personaActualizar.setNumDocu(reniec.getNumeroDocumento());
        personaActualizar.setApePat(reniec.getApellidoPaterno());
        personaActualizar.setApeMat(reniec.getApellidoMaterno());
        personaActualizar.setUsuaModif(Constants.AUDIT_ADMIN);
        personaActualizar.setDateModif(getTimestamp());
        return personaActualizar;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

}
