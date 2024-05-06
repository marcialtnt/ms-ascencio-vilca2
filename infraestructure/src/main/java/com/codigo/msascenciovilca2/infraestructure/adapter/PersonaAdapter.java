package com.codigo.msascenciovilca2.infraestructure.adapter;

import com.codigo.msascenciovilca2.domain.aggregates.constants.Constant;
import com.codigo.msascenciovilca2.domain.aggregates.dto.PersonaDto;
import com.codigo.msascenciovilca2.domain.aggregates.dto.ReniecDto;
import com.codigo.msascenciovilca2.domain.aggregates.request.PersonaRequest;
import com.codigo.msascenciovilca2.domain.ports.out.PersonaServiceOut;
import com.codigo.msascenciovilca2.infraestructure.client.ReniecClient;
import com.codigo.msascenciovilca2.infraestructure.dao.PersonaRepository;
import com.codigo.msascenciovilca2.infraestructure.entity.PersonaEntity;
import com.codigo.msascenciovilca2.infraestructure.mapper.PersonaMapper;
import com.codigo.msascenciovilca2.infraestructure.redis.RedisService;
import com.codigo.msascenciovilca2.infraestructure.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    private final ReniecClient reniecClient;
    private final RedisService redisService;
    @Value("${token.reniec}")
    private String tokenReniec;
    @Override
    public PersonaDto crearPersonaOut(PersonaRequest personaRequest) {
        PersonaEntity personaEntity = getEntity(personaRequest);
        return PersonaMapper.fromEntity(personaRepository.save(personaEntity));
    }

    private PersonaEntity getEntity(PersonaRequest personaRequest) {
        // Ejecutar servicio

        ReniecDto reniecDto = getExecReniec(personaRequest.getNumDoc());
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setNombre(reniecDto.getNombres());
        personaEntity.setApellido(reniecDto.getApellidoPaterno());
        //reniecDto.getApellidoMaterno()
        //personaEntity.setTipoDocumento(Integer.valueOf(reniecDto.getTipoDocumento()));
        return personaEntity;

    }

    private ReniecDto getExecReniec(String numDoc) {
        String authorization = "Bearer " + tokenReniec;
        return reniecClient.getInfoReniec(numDoc, authorization);
    }

    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }
    @Override
    public Optional<PersonaDto> buscarxIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERPERSONA + id);
        if (redisInfo != null) {
            try {
                PersonaDto personaDto = Util.convertirDesdeString(redisInfo, PersonaDto.class);
                return Optional.of(personaDto);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            PersonaDto personaDto = PersonaMapper.fromEntity(personaRepository.findById(id).get());
            try {
                String dataForRedis = Util.convertirPersonaAString(personaDto);
                redisService.saveInRedis(Constant.REDIS_KEY_OBTENERPERSONA + id, dataForRedis, Constant.REDIS_TIEMPO_EXPIRACION);
                return Optional.of(personaDto);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<PersonaDto> buscartodosOut() {
        List<PersonaDto> listaPersonaDto = new ArrayList<>();
        List<PersonaEntity> personaEntities = personaRepository.findAll();
        String authorization = "Bearer " + tokenReniec;
        for (PersonaEntity p : personaEntities) {
            listaPersonaDto.add(PersonaMapper.fromEntity(p));
        }
        return listaPersonaDto;
    }

    @Override
    public PersonaDto actualizarOut(Long id, PersonaRequest personaRequest) {
        Optional<PersonaEntity> datoExtraido = personaRepository.findById(id);
        if (datoExtraido.isPresent()) {
            PersonaEntity personaEntity = getEntity(personaRequest);
            // Agregar auditoría aqui:
            return PersonaMapper.fromEntity(personaEntity);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Override
    public PersonaDto eliminarOut(Long id) {
        Optional<PersonaEntity> datoExtraido = personaRepository.findById(id);
        if (datoExtraido.isPresent()) {
            datoExtraido.get().setEstado(0);
            datoExtraido.get().setUsuaDelet(Constant.USU_ADMIN);
            datoExtraido.get().setDateDelet(getTimestamp());
            // Agregar auditoría aqui:
            return PersonaMapper.fromEntity(personaRepository.save(datoExtraido.get()));
        }
        else {
            throw new RuntimeException();
        }
    }
}
