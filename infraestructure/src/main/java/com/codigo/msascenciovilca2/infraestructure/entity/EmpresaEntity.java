package com.codigo.msascenciovilca2.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "empresa_info")
public class EmpresaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "razonsocial", nullable = false, length = 255)
    private String razonSocial;

    @Column(name = "tipodocumento", nullable = false, columnDefinition = "varchar(5)")
    private String tipoDocumento;

    @Column(name = "numerodocumento", unique = true, nullable = false, length = 20)
    private String numeroDocumento;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "condicion", nullable = false, length = 50)
    private String condicion;

    @Column(name = "distrito", length = 100)
    private String distrito;

    @Column(name = "provincia", length = 100)
    private String provincia;

    @Column(name = "departamento", length = 100)
    private String departamento;

    @Column(name = "esagenteretencion", nullable = false, columnDefinition = "boolean default false")
    Boolean EsAgenteRetencion;

    @Column(name = "usuacrea", nullable = false, length = 255)
    private String usuaCrea;

    @Column(name = "datecreate", nullable = false)
    private Timestamp dateCreate;

    @Column(name = "usuamodif", length = 255)
    private String usuaModif;

    @Column(name = "datemodif")
    private Timestamp dateModif;

    @Column(name = "usuadelet", length = 255)
    private String usuaDelet;

    @Column(name = "datedelet")
    private Timestamp dateDelet;
/*
    @OneToMany(mappedBy="empresa_info")
    private Set<PersonaEntity> personas;
*/
}
