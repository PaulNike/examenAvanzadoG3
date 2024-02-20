package com.codigo.msrodriguezmijahuanga.infraestructure.rest.client;

import com.codigo.msrodriguezmijahuanga.domain.aggregates.response.ResposeReniec;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cliente-reniec", url = "https://api.apis.net.pe/v2/reniec/")
public interface ClienteReniec {
    @GetMapping("/dni")
    ResposeReniec getInfoReniec(@RequestParam("numero") String numero,
                                @RequestHeader("Authorization") String authorization);
}
