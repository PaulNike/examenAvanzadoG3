package com.codigo.msrodriguezmijahuanga.application.controller;

import com.codigo.msrodriguezmijahuanga.domain.aggregates.dto.PersonaDTO;
import com.codigo.msrodriguezmijahuanga.domain.aggregates.request.RequestPersona;
import com.codigo.msrodriguezmijahuanga.domain.ports.in.PersonaServiceIn;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "API-PERSONA",
                version = "2.0",
                description = "Mantenimiento de una Persona"
        )
)
@RestController
@RequestMapping("/v2/persona")
public class PersonaController {

    @Autowired
    @Qualifier("personaServiceImpl")
    private PersonaServiceIn personaServiceIn;

    @Autowired
    @Qualifier("personaServiceImplOther")
    private PersonaServiceIn personaServiceInOther;

    @Operation(summary = "Api para crear una Persona")
    @PostMapping
    public ResponseEntity<PersonaDTO> registrar(@RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(requestPersona));
    }

    @Operation(summary = "Api para obtener datos de una Persona")
    @GetMapping("/{numDoc}")
    public ResponseEntity<PersonaDTO>obtenerPersona(@PathVariable String numDoc){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerPersonaIn(numDoc).get());

    }
    @Operation(summary = "Api para obtener datos de todas Personas Activas")
    @GetMapping()
    public ResponseEntity<List<PersonaDTO>>obtenerTodos(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerTodosIn());

    }

    @Operation(summary = "Api para actualziar los datos de una Persona")
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO>actualizar(@PathVariable Long id,@RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceInOther.actualizarIn(id,requestPersona));

    }

    @Operation(summary = "Api para Borrar datos de una Personas, Eliminado Logico Status =0")
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaDTO>delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceInOther.deleteIn(id));

    }
}
