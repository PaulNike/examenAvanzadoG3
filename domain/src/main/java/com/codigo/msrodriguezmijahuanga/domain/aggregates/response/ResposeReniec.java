package com.codigo.msrodriguezmijahuanga.domain.aggregates.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResposeReniec {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoDocumento;
    private String numeroDocumento;
    private String digitoVerificador;
}
