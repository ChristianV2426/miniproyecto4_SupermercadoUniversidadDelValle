/*
    Archivo: EntidadSalud.java
    Fundamentos de Programación Orientada a Eventos - 750014C Grupo 01
    Proyecto 3 - Servicio de Salud Universidad del Valle

    Autores: 
    Juan Camilo Narvaez Tascon - juan.narvaez.tascon@correounivalle.edu.co - 2140112-3743
    Christian David Vargas Gutiérrez - vargas.christian@correounivalle.edu.co - 2179172-3743

    Profesor:
    Ing. M.Sc. Luis Yovany Romo Portilla

    Licencia: GNU-GPL
*/

/**
    CLASE: EntidadSalud

    INTENCIÓN: Esta clase modela la entidad de salud.
    En este programa en particular, la entidad de salud es quien administra los elementos del sistema: los afiliados, médicos, servicios médicos, consultorios y citas.

    RELACIONES:
    -Contiene 5 objetos de tipo DAO, uno por cada tipo de objeto del sistema (afiliado, médico, servicio médico, consultorio, cita).
*/

package co.edu.univalle.modelo;

import co.edu.univalle.dao.*;
import java.io.Serializable;

public class EntidadSalud implements Serializable{
    public static final long serialVersionUID = 1L;
    private String nombreEntidad;
    private AfiliadoDao daoAfiliados;
    private MedicoDao daoMedicos;
    private ServicioMedicoDao daoServiciosMedicos;
    private ConsultorioDao daoConsultorios;
    private CitaDao daoCitas;
    private Integer serialCitas = 1001;

    public EntidadSalud(String nombreEntidad){
        this.nombreEntidad = nombreEntidad;
        this.daoAfiliados = new AfiliadoDaoImpl();
        this.daoMedicos = new MedicoDaoImpl();
        this.daoServiciosMedicos = new ServicioMedicoDaoImpl();
        this.daoConsultorios = new ConsultorioDaoImpl();
        this.daoCitas = new CitaDaoImpl();
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public AfiliadoDao getDaoAfiliados() {
        return daoAfiliados;
    }

    public void setDaoAfiliados(AfiliadoDao daoAfiliados) {
        this.daoAfiliados = daoAfiliados;
    }

    public MedicoDao getDaoMedicos() {
        return daoMedicos;
    }

    public void setDaoMedicos(MedicoDao daoMedicos) {
        this.daoMedicos = daoMedicos;
    }

    public ServicioMedicoDao getDaoServiciosMedicos() {
        return daoServiciosMedicos;
    }

    public void setDaoServiciosMedicos(ServicioMedicoDao daoServiciosMedicos) {
        this.daoServiciosMedicos = daoServiciosMedicos;
    }

    public ConsultorioDao getDaoConsultorios() {
        return daoConsultorios;
    }

    public void setDaoConsultorios(ConsultorioDao daoConsultorios) {
        this.daoConsultorios = daoConsultorios;
    }

    public CitaDao getDaoCitas() {
        return daoCitas;
    }

    public void setDaoCitas(CitaDao daoCitas) {
        this.daoCitas = daoCitas;
    }

    public Integer getSerialCitas() {
        return serialCitas;
    }

    public void setSerialCitas(Integer nuevoSerialCitas) {
        this.serialCitas = nuevoSerialCitas;
    }

    public void summarSerialCitas(){
        serialCitas++;
    }
}
