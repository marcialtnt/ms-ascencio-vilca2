package com.codigo.msascenciovilca2.infraestructure.adapter;

import com.codigo.msascenciovilca2.domain.aggregates.constants.Constant;
import com.codigo.msascenciovilca2.domain.aggregates.dto.EmpresaDto;
import com.codigo.msascenciovilca2.domain.aggregates.dto.SunatDto;
import com.codigo.msascenciovilca2.domain.aggregates.request.EmpresaRequest;
import com.codigo.msascenciovilca2.domain.ports.out.EmpresaServiceOut;
import com.codigo.msascenciovilca2.infraestructure.client.SunatClient;
import com.codigo.msascenciovilca2.infraestructure.dao.EmpresaRepository;
import com.codigo.msascenciovilca2.infraestructure.entity.EmpresaEntity;
import com.codigo.msascenciovilca2.infraestructure.mapper.EmpresaMapper;
import com.codigo.msascenciovilca2.infraestructure.redis.RedisService;
import com.codigo.msascenciovilca2.infraestructure.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.x509.sigi.PersonalData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaAdapter implements EmpresaServiceOut {
    private final EmpresaRepository empresaRepository;
    private final SunatClient sunatClient;
    private final RedisService redisService;

    @Value("${token.reniec}")
    private String tokenReniec;
    @Override
    public EmpresaDto crearEmpresaOut(EmpresaRequest empresaRequest) {
        EmpresaEntity empresaEntity = getEntity(empresaRequest, false, null);
        return EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
    }

    private EmpresaEntity getEntity(EmpresaRequest empresaRequest, boolean actualiza, Integer id) {
        SunatDto sunatDto = getExecSunat(empresaRequest.getNumDoc());
        EmpresaEntity entity = new EmpresaEntity();
        //entity.setTipoDocumento(sunatDto.getTipoDocumento());
        entity.setTipoDocumento("RUC");
        entity.setNumeroDocumento(sunatDto.getNumeroDocumento());
        entity.setRazonSocial(sunatDto.getRazonSocial());
        entity.setCondicion(sunatDto.getCondicion());
        entity.setEsAgenteRetencion(sunatDto.getEsAgenteRetencion());
        entity.setEstado(1);
        if (actualiza) {
            entity.setId(id);
            entity.setUsuaModif(Constant.USU_ADMIN);
            entity.setDateModif(getTimestamp());
        }
        else {
            entity.setUsuaCrea(Constant.USU_ADMIN);
            entity.setDateCreate(getTimestamp());
        }
        return entity;
    }

    private Timestamp getTimestamp() {
        Long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
    private SunatDto getExecSunat(String numDoc) {
        String authorization = "Bearer " + tokenReniec;
        String json = sunatClient.getInfoSunat(numDoc, authorization);
        ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson(); // Or use new GsonBuilder().create();
        SunatDto target = gson.fromJson(json, SunatDto.class); // deserializes json into target2
        return target;
    }
    @Override
    public Optional<EmpresaDto> buscarXIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENEREMPRESA + id);
        if (redisInfo != null) {
            try {
                EmpresaDto empresaDto = Util.convertirDesdeString(redisInfo, EmpresaDto.class);
                return Optional.of(empresaDto);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                EmpresaDto empresaDto = EmpresaMapper.fromEntity(empresaRepository.findById(id).get());
                String dataForRedis = Util.convertirEmpresaAString(empresaDto);
                redisService.saveInRedis(Constant.REDIS_KEY_OBTENEREMPRESA + id, dataForRedis, 2);
                return Optional.of(empresaDto);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<EmpresaDto> buscarTodosOut() {
        List<EmpresaDto> listaDto = new ArrayList<>();
        List<EmpresaEntity> entidades = empresaRepository.findAll();
        for (EmpresaEntity dato : entidades) {
            listaDto.add(EmpresaMapper.fromEntity(dato));
        }
        return listaDto;
    }

    @Override
    public EmpresaDto actualizarOut(Integer id, EmpresaRequest empresaRequest) {
        Optional<EmpresaEntity> datoExtraido = empresaRepository.findById(id.longValue());
        if (datoExtraido.isPresent()) {
            EmpresaEntity empresaEntity = null;
            empresaEntity = getEntity(empresaRequest, true, id);
            return EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
        }
        else {
            throw new RuntimeException();
        }
    }

    @Override
    public EmpresaDto eliminarOut(Integer id) {
        Optional<EmpresaEntity> datoExtraido = empresaRepository.findById(id.longValue());
        if (datoExtraido.isPresent()) {
            datoExtraido.get().setEstado(0);
            datoExtraido.get().setDateDelet(getTimestamp());
            return EmpresaMapper.fromEntity(empresaRepository.save(datoExtraido.get()));
        }
        else {
            throw new RuntimeException();
        }
    }
}
