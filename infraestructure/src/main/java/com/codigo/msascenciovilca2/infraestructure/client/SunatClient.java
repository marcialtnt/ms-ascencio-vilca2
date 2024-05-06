package com.codigo.msascenciovilca2.infraestructure.client;

import com.codigo.msascenciovilca2.domain.aggregates.dto.SunatDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sunat-client", url = "https://api.apis.net.pe/v2/sunat/")
public interface SunatClient {
    @GetMapping("/ruc")
    String getInfoSunat(@RequestParam("numero") String numero,
                          @RequestHeader("Authorization") String authorization);
}
