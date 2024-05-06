package com.codigo.msascenciovilca2.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 255)
    private String apellido;

    @Column(name = "tipoDocumento", nullable = false, length = 5)
    private String tipoDocumento;

    @Column(name = "numeroDocumento", unique = true, nullable = false, length = 20)
    private String numeroDocumento;

    @Column(name = "email", unique = true, nullable = false, length = 255)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "usuaCrea", nullable = false, length = 255)
    private String usuaCrea;

    @Column(name = "dateCreate", nullable = false)
    private Timestamp dateCreate;

    @Column(name = "usuaModif", length = 255)
    private String usuaModif;

    @Column(name = "dateModif")
    private Timestamp dateModif;

    @Column(name = "usuaDelet", length = 255)
    private String usuaDelet;

    @Column(name = "dateDelet")
    private Timestamp dateDelet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_info", nullable = false)
    private EmpresaEntity empresa;
}
